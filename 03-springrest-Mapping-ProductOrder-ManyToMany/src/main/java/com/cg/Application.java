package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
/* Post : http://localhost:9092/api/order
  {
        "date": "2025-09-08T00:00:00.000+00:00",
        "orderNo": 125
}
 GET: http://localhost:9092/api/productlist

[
    {
        "id": 2,
        "pname": "Facewash",
        "price": 100.0
    }
]

post product:http://localhost:9092/api/addproducts

{
    "pname": "Shampoo",
        "price": 200.0
}
 */
 
