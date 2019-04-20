package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.commons.SendEmail;
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
    private AvailabilityService availabilityService;

    private SendEmail sendEmail = new SendEmail();

    @Autowired
    private AvailabilityRepository availabilityRepository;


    @Override
    public Booking create(Booking booking) {
        booking = bookingRepository.save(booking);
        Date fromDate = booking.getCheckIn();
        Date toDate = booking.getCheckOut();
        List<Availability> availabilitiesBooked = booking.getAvailabilityList();
        for (int i = 0; i < availabilitiesBooked.size() ; i++) {
           if (availabilitiesBooked.get(i).getFromDate().compareTo(fromDate)<0){
                Availability currentAvailability =  availabilitiesBooked.get(i);
                Availability availabilityBeforeBookingDateRange = new Availability();
                availabilityBeforeBookingDateRange.setProperty(currentAvailability.getProperty());
                availabilityBeforeBookingDateRange.setFromDate(currentAvailability.getFromDate());
                availabilityBeforeBookingDateRange.setToDate(fromDate);
                availabilityBeforeBookingDateRange.setPriceSingle(currentAvailability.getPriceSingle());
                availabilityBeforeBookingDateRange.setRoomName(currentAvailability.getRoomName());
                availabilityBeforeBookingDateRange.setRoomType(currentAvailability.getRoomType());
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

    @Override
    public void sendBookingEmail(Booking booking, Availability availability) {

        String message = "Dear " + booking.getClient().getName() + ","
                + "\n"
                + "\n" + "Thank you for choosing " + booking.getProperty().getName() + " Hotel" + ". Please find below your booking details: "
                + "\n"
                + "\n" + "Reservation number: " + booking.getBookingNo()
                + "\n" + "Number of rooms: " + booking.getRoomsNo()
                + "\n" + "Room type: " + booking.getRoomType()
                + "\n" + "Number of adults/child: " + booking.getPersonsNo()
                + "\n" + "Arrival date: " + booking.getCheckIn()
                + "\n" + "Departure date: " + booking.getCheckOut()
                + "\n" + "Booking date: " + booking.getBookingDate()
                + "\n"
                + "\n" + "We look forward to welcoming you to " + booking.getProperty().getName() + " Hotel."
                + "\n"
                + "\n" + "Thanks and Regards,"
                + "\n" + "Booking Team";

        String email = booking.getClient().getEmail();
        String subject = "Booking confirmation ";
        boolean isAvailable = availabilityService.existsAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(booking.getCheckIn(),booking.getCheckOut());
        if(!isAvailable){
            sendEmail.sendEmail(message,email,subject);
        }else{
            String nonAvailabilityMessage = "Apologies, currently we have no available rooms in the given period.";
            sendEmail.sendEmail(nonAvailabilityMessage,email,subject);
        }
    }
}
