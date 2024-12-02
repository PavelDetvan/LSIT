# LSIT
Roles in Project:

Admins:
- Permissions: Admins can manage everything in the application, such as adding, updating, deleting cars, customers, and bookings.
- Endpoints Accessible: All endpoints are accessible to admins.

Employees:
- Permissions: Employees may assist with managing bookings but cannot delete cars or modify customer details. They can add new bookings and update car availability.
- Endpoints Accessible: Most booking-related operations and viewing cars/customers.

Customers:
- Permissions: Customers can create bookings and view their existing bookings. They cannot add or delete cars or update customer information of others.
- Endpoints Accessible: Viewing cars, creating bookings, and viewing customer profile (restricted to their own data).