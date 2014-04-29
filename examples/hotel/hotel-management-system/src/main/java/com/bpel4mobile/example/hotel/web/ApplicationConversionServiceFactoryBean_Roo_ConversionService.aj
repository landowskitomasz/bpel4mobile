// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.bpel4mobile.example.hotel.web;

import com.bpel4mobile.example.hotel.entity.Category;
import com.bpel4mobile.example.hotel.entity.Room;
import com.bpel4mobile.example.hotel.entity.RoomReservation;
import com.bpel4mobile.example.hotel.repository.CategoryRepository;
import com.bpel4mobile.example.hotel.repository.RoomReservationRepository;
import com.bpel4mobile.example.hotel.service.RoomService;
import com.bpel4mobile.example.hotel.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    CategoryRepository ApplicationConversionServiceFactoryBean.categoryRepository;
    
    @Autowired
    RoomService ApplicationConversionServiceFactoryBean.roomService;
    
    @Autowired
    RoomReservationRepository ApplicationConversionServiceFactoryBean.roomReservationRepository;
    
    public Converter<Category, String> ApplicationConversionServiceFactoryBean.getCategoryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.bpel4mobile.example.hotel.entity.Category, java.lang.String>() {
            public String convert(Category category) {
                return new StringBuilder().append(category.getName()).append(' ').append(category.getStandard()).toString();
            }
        };
    }
    
    public Converter<Long, Category> ApplicationConversionServiceFactoryBean.getIdToCategoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.bpel4mobile.example.hotel.entity.Category>() {
            public com.bpel4mobile.example.hotel.entity.Category convert(java.lang.Long id) {
                return categoryRepository.findOne(id);
            }
        };
    }
    
    public Converter<String, Category> ApplicationConversionServiceFactoryBean.getStringToCategoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.bpel4mobile.example.hotel.entity.Category>() {
            public com.bpel4mobile.example.hotel.entity.Category convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Category.class);
            }
        };
    }
    
    public Converter<Room, String> ApplicationConversionServiceFactoryBean.getRoomToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.bpel4mobile.example.hotel.entity.Room, java.lang.String>() {
            public String convert(Room room) {
                return new StringBuilder().append(room.getNumber()).append(' ').append(room.getFloor()).toString();
            }
        };
    }
    
    public Converter<Long, Room> ApplicationConversionServiceFactoryBean.getIdToRoomConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.bpel4mobile.example.hotel.entity.Room>() {
            public com.bpel4mobile.example.hotel.entity.Room convert(java.lang.Long id) {
                return roomService.findRoom(id);
            }
        };
    }
    
    public Converter<String, Room> ApplicationConversionServiceFactoryBean.getStringToRoomConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.bpel4mobile.example.hotel.entity.Room>() {
            public com.bpel4mobile.example.hotel.entity.Room convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Room.class);
            }
        };
    }
    
    public Converter<RoomReservation, String> ApplicationConversionServiceFactoryBean.getRoomReservationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.bpel4mobile.example.hotel.entity.RoomReservation, java.lang.String>() {
            public String convert(RoomReservation roomReservation) {
                return new StringBuilder().append(roomReservation.getCustomerFirstName()).append(' ').append(roomReservation.getCustomerLastName()).append(' ').append(roomReservation.getStartDate()).append(' ').append(roomReservation.getEndDate()).toString();
            }
        };
    }
    
    public Converter<Long, RoomReservation> ApplicationConversionServiceFactoryBean.getIdToRoomReservationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.bpel4mobile.example.hotel.entity.RoomReservation>() {
            public com.bpel4mobile.example.hotel.entity.RoomReservation convert(java.lang.Long id) {
                return roomReservationRepository.findOne(id);
            }
        };
    }
    
    public Converter<String, RoomReservation> ApplicationConversionServiceFactoryBean.getStringToRoomReservationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.bpel4mobile.example.hotel.entity.RoomReservation>() {
            public com.bpel4mobile.example.hotel.entity.RoomReservation convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), RoomReservation.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getCategoryToStringConverter());
        registry.addConverter(getIdToCategoryConverter());
        registry.addConverter(getStringToCategoryConverter());
        registry.addConverter(getRoomToStringConverter());
        registry.addConverter(getIdToRoomConverter());
        registry.addConverter(getStringToRoomConverter());
        registry.addConverter(getRoomReservationToStringConverter());
        registry.addConverter(getIdToRoomReservationConverter());
        registry.addConverter(getStringToRoomReservationConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}