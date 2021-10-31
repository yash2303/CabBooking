POST http://localhost:8080/cab/register
Content-Type: application/json

{
"licenseNumber": "RJ28 1234",
"locationX": 3.0,
"locationY": 4.0
}

###
POST http://localhost:8080/rider/register
Content-Type: application/json

{
"userId": "userId1",
"name": "John Doe",
"email": "john@doe.com"
}


###
POST http://localhost:8080/cab/updateLocation
Content-Type: application/json

{
"licenseNumber": "RJ28 1234",
"locationX": 1.0,
"locationY": 1.0
}


###
POST http://localhost:8080/rider/bookCab
Content-Type: application/json

{
"riderId": "userId1",
"sourceX": 0.0,
"sourceY": 0.0,
"destinationX": 10.0,
"destinationY": 10.0
}


###
POST http://localhost:8080/cab/updateAvailability
Content-Type: application/json

{
"licenseNumber": "RJ28 1234",
"availability": true
}


###
GET http://localhost:8080/cab/getBookingHistory
Content-Type: text/plain

RJ28 1234


###
POST http://localhost:8080/cab/endTrip
Content-Type: text/plain

RJ28 1234


###
GET http://localhost:8080/rider/getBookingHistory
Content-Type: text/plain

userId1
