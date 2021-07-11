//package com.marketplace.service;
//
//import com.marketplace.exception.Conflict;
//import com.marketplace.exception.CustomConflictException;
//import com.marketplace.model.Media;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Optional;
//
//@Service
//public class MediaService {
//
//    private static String path = "/files";
//
//    @Autowired
//    EmployeeRepository employeeRepository;
//
//    public String addMediaToEmployee(String fileName,
//                                     Long employeeId,
//                                     String type) throws CustomConflictException {
//        if (type.equals(".mp4")) {
//            throw new CustomConflictException(Conflict.FILE_NOT_SUPPORTED);
//        }
//
//        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
//
//        if (optionalEmployee.isPresent()) {
//            long now = System.currentTimeMillis();
//            Employee employee = optionalEmployee.get();
//            Media mediaFile = new Media(path, fileName, ".jpg", now);
//            LocalDate localDate =
//                    Instant.ofEpochMilli(now)
//                            .atZone(ZoneId.systemDefault())
//                            .toLocalDate();
//            System.out.println("localDate: " + localDate);
//            employee.setProfileImage(mediaFile);
//
//            employeeRepository.save(employee);
//
//            return "saved file";
//        } return "not saved";
//
//    }
//}
