# LSIT Car Rental Service Project
## Overview
A web application for managing car rentals, built with **Java (Spring Boot)**. It features **role-based access control (RBAC)** using **GitLab OAuth2** and **AWS S3** for data persistence, with in-memory storage for local testing.

## Features
- **Role-Based Access Control** via **GitLab OAuth2**:
    Admins:
    - Permissions: Admins can manage everything in the application, such as adding, updating, deleting cars, customers, and bookings.
    - Endpoints Accessible: All endpoints are accessible to admins.
    
    Employees:
    - Permissions: Employees may assist with managing bookings but cannot delete cars or modify customer details. They can add new bookings and update car availability.
    - Endpoints Accessible: Most booking-related operations and viewing cars/customers.
    
    Customers:
    - Permissions: Customers can create bookings and view their existing bookings. They cannot add or delete cars or update customer information of others.
    - Endpoints Accessible: Viewing cars, creating bookings, and viewing customer profile (restricted to their own data).

- **REST API Endpoints**:
  - **Cars**, **Customers**, and **Bookings**: CRUD operations.
  - **Coupon Info**: Google Cloud coupon details.

- **Cloud Persistence** with **AWS S3** alongside in-memory repositories.

## Technologies
- **Java (Spring Boot)**, **OAuth2 with GitLab**, **AWS S3**, **Docker** (in progress).

## Setup
1. **Clone Repository**:
   ```bash
   git clone https://github.com/your-username/car-rental-service.git
   cd car-rental-service


