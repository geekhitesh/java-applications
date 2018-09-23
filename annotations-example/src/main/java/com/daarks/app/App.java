package com.daarks.app;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Car car = new Car("Ford", "F150", "2018");
		JsonSerializer serializer = new JsonSerializer();
		try {
			serializer.serialize(car);
		} catch (JsonSerializeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
