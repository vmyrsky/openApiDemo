package com.org.product.generic.processor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestEntity {
    public String id;
    public boolean flag;
    public int number;
    public double decimal;
    public LocalDate date;
    public LocalTime time;
    public ZonedDateTime zonedDateTime;
    @JsonIgnore
    public String ignored;
    @JsonProperty("named")
    public String renamed;

    /**
     * Default constructor must exist when automatic entity populating is done by Jackson.
     */
    public TestEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getDecimal() {
        return decimal;
    }

    public void setDecimal(double decimal) {
        this.decimal = decimal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public String getIgnored() {
        return ignored;
    }

    public void setIgnored(String ignored) {
        this.ignored = ignored;
    }

    public String getRenamed() {
        return renamed;
    }

    public void setRenamed(String renamed) {
        this.renamed = renamed;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TestEntity [id=");
        builder.append(id);
        builder.append(", flag=");
        builder.append(flag);
        builder.append(", number=");
        builder.append(number);
        builder.append(", decimal=");
        builder.append(decimal);
        builder.append(", date=");
        builder.append(date);
        builder.append(", time=");
        builder.append(time);
        builder.append(", zonedDateTime=");
        builder.append(zonedDateTime);
        builder.append(", ignored=");
        builder.append(ignored);
        builder.append(", renamed=");
        builder.append(renamed);
        builder.append("]");
        return builder.toString();
    }
}
