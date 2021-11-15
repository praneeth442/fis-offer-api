springBoot offer service

Requirements
For building and running the application you need:
   1. JDK 1.8
   
   2 .Maven 3

Running the application locally
  
    mvn spring-boot:run


NOTE :
1. Used embedded in memory data base (h2) so data would be lost if we start or stop service
2. Implemented  validation to mandatory fields (id,validFrom,validTo)
3. Implemented unit test cases to service & validation layer

Pending :
1. Add integration test
2. creating a docker image 



 Offer services

  1. Create/update offer
    http method : POST
    request header Content-Type: application/json
     This service creates or updates an offer 

      Sample Request  
          http://localhost:8080/service/createoffer
           {
	          "id": "8967",
	          "description": "123",
	          "name": "123",
	          "validFrom": "2021-11-14T22:20",
	          "validTo": "2021-11-14T22:20"
           }

          Request Params
                  id -> it a mandatory field of type String (which is considered as primary unique key in data base)
                  description -> its a option field  of type String to provide meta info to an  offer 
                  name -> its a optional field of type String to provide name for  on offer
                  validFrom -> its a mandatory field of type String (which accepts ISO date-time ('2011-12-03T10:15:30') )
                  validTo -> its a mandatory field of type String (which accepts ISO date-time ('2011-12-03T10:15:30') )
 
            Sample Response
         {
          "message": "Offer Created"
         }

         Sample validation error message

          {
           "message": "missing mandatory value for the param : id"
           }


  2. GetOffer
      http method : GET
        This service returns a  valid offer found with id and offer is active now
        offer is active if current time or system time is in between validFrom & validTo values (passed during offer creation)
 
        Sample Request http://localhost:8080/service/offer/{id}
 
        below response is 
          {
            "id": "llkk",
            "description": "123",
            "name": "123",
            "status": "ACTIVE",
            "validFrom": "2021-11-14T22:20",
            "validTo": "2021-11-14T23:50"
           }
            response params
            status-> its indicates whether is valid (Active) or in valid (INACTIVE)
 
  3. GET All Offers
        http method : GET
        It returns all offers created by user (whether inactive or active)

        http://localhost:8080/service/offers
 
            sample response
             [
                {
                "id": "llkk",
                "description": "123",
                "name": "123",
                "status": "ACTIVE",
                "validFrom": "2021-11-14T22:20",
                "validTo": "2021-11-14T23:50"
             },
             {
                "id": "llkk1",
                "description": "123",
                "name": "123",
                "status": "INACTIVE",
                "validFrom": "2021-11-14T22:20",
                "validTo": "2021-11-14T22:50"
             }
            ]
 
  4. delete Offer
        http method : DELETE
        This service deletes or removes offer from data base
 
        sample request
        http://localhost:8080/service/removeoffer/123
 
        sample response
 
        {   
            "message": "Offer with id 123 not found"
        }


