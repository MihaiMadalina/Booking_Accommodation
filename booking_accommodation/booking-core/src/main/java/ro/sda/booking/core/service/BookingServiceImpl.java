package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.repository.AvailabilityRepository;
import ro.sda.booking.core.repository.BookingRepository;

import java.util.Date;
import java.util.List;

@Service("bookingService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AvailabilityService availabilityService;

//    @Override
//    public Booking create(Booking booking) {
//        return bookingRepository.save(booking);
//    }

//    @Override
//    public Booking create(Booking booking) {
//        booking = bookingRepository.save(booking);
//        Date fromDate = booking.getCheckIn();
//        Date toDate = booking.getCheckOut();
//        List<Availability> availabilitiesBooked = booking.getAvailabilityList();
//        for (int i = 0; i < availabilitiesBooked.size() ; i++) {
//            if (availabilitiesBooked.get(i).getFromDate().compareTo(fromDate)==0 && availabilitiesBooked.get(i).getToDate().compareTo(toDate)==0){
//                availabilitiesBooked.get(i).setAvailable(false);
//                availabilityService.update(availabilitiesBooked.get(i));
//            } else if (availabilitiesBooked.get(i).getFromDate().compareTo(fromDate)<0 && availabilitiesBooked.get(i).getToDate().compareTo(toDate)>0 ){
//                Availability currentAvailability =  availabilitiesBooked.get(i);
//                currentAvailability.setAvailable(false);
//                availabilityService.update(currentAvailability);
//                Availability availabilityBeforeBookingDateRange = new Availability();
//                availabilityBeforeBookingDateRange.setProperty(currentAvailability.getProperty());
//                availabilityBeforeBookingDateRange.setFromDate(currentAvailability.getFromDate());
//                availabilityBeforeBookingDateRange.setToDate(fromDate);
//                availabilityBeforeBookingDateRange.setPriceSingle(currentAvailability.getPriceDouble());
//                availabilityBeforeBookingDateRange.setRoomName(currentAvailability.getRoomName());
//                availabilityBeforeBookingDateRange.setRoomType(currentAvailability.getRoomType());
//                availabilityBeforeBookingDateRange.setAvailable(true);
//                availabilityService.create(availabilityBeforeBookingDateRange);
//                Availability availabilityAfterBookingDateRange = new Availability();
//                availabilityAfterBookingDateRange.setProperty(currentAvailability.getProperty());
//                availabilityAfterBookingDateRange.setFromDate(toDate);
//                availabilityAfterBookingDateRange.setToDate(currentAvailability.getToDate());
//                availabilityAfterBookingDateRange.setPriceSingle(currentAvailability.getPriceDouble());
//                availabilityAfterBookingDateRange.setRoomName(currentAvailability.getRoomName());
//                availabilityAfterBookingDateRange.setRoomType(currentAvailability.getRoomType());
//                availabilityAfterBookingDateRange.setAvailable(true);
//                availabilityService.create(availabilityAfterBookingDateRange);
//            } else if (availabilitiesBooked.get(i).getFromDate().compareTo(fromDate)<0 && availabilitiesBooked.get(i).getToDate().compareTo(toDate)==0 ){
//                Availability currentAvailability =  availabilitiesBooked.get(i);
//                currentAvailability.setAvailable(false);
//                availabilityService.update(currentAvailability);
//                Availability availabilityBeforeBookingDateRange = new Availability();
//                availabilityBeforeBookingDateRange.setProperty(currentAvailability.getProperty());
////                availabilityBeforeBookingDateRange.setFromDate(currentAvailability.getFromDate());
////                availabilityBeforeBookingDateRange.setToDate(fromDate);
////                availabilityBeforeBookingDateRange.setPriceSingle(currentAvailability.getPriceDouble());
////                availabilityBeforeBookingDateRange.setRoomName(currentAvailability.getRoomName());
////                availabilityBeforeBookingDateRange.setRoomType(currentAvailability.getRoomType());
////                availabilityBeforeBookingDateRange.setAvailable(true);
////                availabilityService.create(availabilityBeforeBookingDateRange);
//            }
//            else if (availabilitiesBooked.get(i).getFromDate().compareTo(fromDate)==0 && availabilitiesBooked.get(i).getToDate().compareTo(toDate)>0 ){
//                Availability currentAvailability =  availabilitiesBooked.get(i);
//                currentAvailability.setAvailable(false);
//                availabilityService.update(currentAvailability);
//                 Availability availabilityAfterBookingDateRange = new Availability();
////                availabilityAfterBookingDateRange.setProperty(currentAvailability.getProperty());
////                availabilityAfterBookingDateRange.setFromDate(toDate);
////                availabilityAfterBookingDateRange.setToDate(currentAvailability.getToDate());
////                availabilityAfterBookingDateRange.setPriceSingle(currentAvailability.getPriceDouble());
////                availabilityAfterBookingDateRange.setRoomName(currentAvailability.getRoomName());
////                availabilityAfterBookingDateRange.setRoomType(currentAvailability.getRoomType());
////                availabilityAfterBookingDateRange.setAvailable(true);
////                availabilityService.create(availabilityAfterBookingDateRange);
//            }
//        }
//        return booking;
//    }

    @Override
    public Booking create(Booking booking) {
        booking = bookingRepository.save(booking);
        Date fromDate = booking.getCheckIn();
        Date toDate = booking.getCheckOut();
        List<Availability> availabilitiesBooked = booking.getAvailabilityList();
        for (int i = 0; i < availabilitiesBooked.size() ; i++) {
//                availabilitiesBooked.get(i).setAvailable(false);
//                availabilitiesBooked.get(i).setBooking(booking);
//                availabilityService.update(availabilitiesBooked.get(i));
           if (availabilitiesBooked.get(i).getFromDate().compareTo(fromDate)<0){
                Availability currentAvailability =  availabilitiesBooked.get(i);
                Availability availabilityBeforeBookingDateRange = new Availability();
                availabilityBeforeBookingDateRange.setProperty(currentAvailability.getProperty());
                availabilityBeforeBookingDateRange.setFromDate(currentAvailability.getFromDate());
                availabilityBeforeBookingDateRange.setToDate(fromDate);
                availabilityBeforeBookingDateRange.setPriceSingle(currentAvailability.getPriceSingle());
                availabilityBeforeBookingDateRange.setRoomName(currentAvailability.getRoomName());
                availabilityBeforeBookingDateRange.setRoomType(currentAvailability.getRoomType());
                availabilityBeforeBookingDateRange.setAvailable(true);
                availabilityService.create(availabilityBeforeBookingDateRange);

            }
            if (availabilitiesBooked.get(i).getToDate().compareTo(toDate)>0 ){
                Availability currentAvailability =  availabilitiesBooked.get(i);
               Availability availabilityAfterBookingDateRange = new Availability();
               availabilityAfterBookingDateRange.setProperty(currentAvailability.getProperty());
               availabilityAfterBookingDateRange.setFromDate(toDate);
               availabilityAfterBookingDateRange.setToDate(currentAvailability.getToDate());
               availabilityAfterBookingDateRange.setPriceSingle(currentAvailability.getPriceSingle());
               availabilityAfterBookingDateRange.setRoomName(currentAvailability.getRoomName());
               availabilityAfterBookingDateRange.setRoomType(currentAvailability.getRoomType());
               availabilityAfterBookingDateRange.setAvailable(true);
               availabilityService.create(availabilityAfterBookingDateRange);
            }

            availabilityService.delete(availabilitiesBooked.get(i));
        }
        return booking;
    }


    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }
}
