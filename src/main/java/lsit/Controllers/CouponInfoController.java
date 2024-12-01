package lsit.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CouponInfoController {

    @Value("${google-cloud.coupon-info.url}")
    private String couponUrl;

    @Value("${google-cloud.coupon-info.request-info.details}")
    private String requestDetails;

    @Value("${google-cloud.coupon-info.request-until}")
    private String requestUntil;

    @Value("${google-cloud.coupon-info.coupon-valid-until}")
    private String couponValidUntil;

    @Value("${google-cloud.coupon-info.note}")
    private String note;

    @GetMapping("/coupon-info")
    public Map<String, String> getCouponInfo() {
        return Map.of(
            "url", couponUrl,
            "details", requestDetails,
            "requestUntil", requestUntil,
            "couponValidUntil", couponValidUntil,
            "note", note
        );
    }
}
