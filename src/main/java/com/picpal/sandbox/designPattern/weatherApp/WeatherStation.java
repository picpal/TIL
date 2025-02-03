package com.picpal.sandbox.designPattern.weatherApp;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData  = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80,65,30.3f);
        weatherData.setMeasurements(82,70,29.3f);
        weatherData.setMeasurements(78,90,28.3f);
    }
}
