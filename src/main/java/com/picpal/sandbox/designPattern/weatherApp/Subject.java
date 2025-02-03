package com.picpal.sandbox.designPattern.weatherApp;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
