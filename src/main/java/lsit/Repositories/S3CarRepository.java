package lsit.Repositories;

import java.net.URI;
import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lsit.Models.Car;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

@Primary
@Repository
public class S3CarRepository implements ICarRepository {
    final String BUCKET="lsit-example-bucket";
    final String PREFIX="carrental/cars/";
    final String ACCESS_KEY="YOUR_ACCESS_KEY_HERE";
    final String SECRET_KEY="YOUR_SECRET_KEY_HERE";
    final String ENDPOINT_URL="https://storage.googleapis.com";

    S3Client s3client;
    AwsCredentials awsCredentials;
    
    public S3CarRepository(){
        awsCredentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);
        s3client = S3Client.builder()
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .endpointOverride(URI.create(ENDPOINT_URL))
            .region(Region.of("auto"))
            .build();
    }

    public void add(Car car){
        try{
            car.id = UUID.randomUUID();

            ObjectMapper om = new ObjectMapper();

            String carJson = om.writeValueAsString(car);
            
            s3client.putObject(PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(PREFIX + car.id.toString())
                .build(),
                RequestBody.fromString(carJson)
            );
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public Car get(UUID id){
        try{
            var objectBytes = s3client.getObject(GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(PREFIX + id.toString())
                .build()
            ).readAllBytes();

            ObjectMapper om = new ObjectMapper();
            Car car = om.readValue(objectBytes, Car.class);

            return car;
        }catch(Exception e){
            return null;
        }
    }

    public void remove(UUID id){
        s3client.deleteObject(DeleteObjectRequest.builder()
            .bucket(BUCKET)
            .key(PREFIX + id.toString())
            .build()
        );  
    }

    public void update(Car car){
        try{
            Car existingCar = this.get(car.id);
            if(existingCar == null) return;

            ObjectMapper om = new ObjectMapper();
            String carJson = om.writeValueAsString(car);
            s3client.putObject(PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(PREFIX + car.id.toString())
                .build(),
                RequestBody.fromString(carJson)
            );
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public List<Car> list(){
        List<Car> cars = new ArrayList<>();
        List<S3Object> objects = s3client.listObjects(ListObjectsRequest.builder()
          .bucket(BUCKET)
          .prefix(PREFIX)
          .build()  
        ).contents();

        for(S3Object o : objects){
            Car car = new Car("", "", 0.0);
            car.id = UUID.fromString(o.key().substring(PREFIX.length()));
            cars.add(car);
        }

        return cars;
    }
}
