package ro.sda.booking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.commons.SendEmail;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.repository.BookingRepository;

import java.util.List;

@Service("bookingService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AvailabilityService availabilityService;

    private SendEmail sendEmail = new SendEmail();

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
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
