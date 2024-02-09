package com.livrariamabuko.Livraria.Mabuko.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.model.EmailDetails;
import com.livrariamabuko.Livraria.Mabuko.model.EmailStatus;
import com.livrariamabuko.Livraria.Mabuko.repository.EmailRepository;

@Service
public class EmailService {
       @Autowired
     EmailRepository emailRepository;
   
    //  @Autowired
    //  private JavaMailSender emailSender;

    //  public EmailDetails sendEmail(EmailDetails emailDetails) {
    //      emailDetails.setSendDateEmail(LocalDateTime.now());
    //      try{
    //          SimpleMailMessage message = new SimpleMailMessage();
    //          message.setFrom(emailDetails.getEmailFrom());
    //          message.setTo(emailDetails.getEmailTo());
    //          message.setSubject(emailDetails.getSubject());
    //          message.setText(emailDetails.getText());
    //          emailSender.send(message);

    //          emailDetails.setStatusEmail(EmailStatus.SENT);
    //      } catch (MailException e){
    //          emailDetails.setStatusEmail(EmailStatus.ERROR);
    //      } finally {
    //         return emailRepository.save(emailDetails);

    //      }
    //  }

    //  public Page<EmailDetails> findAll(Pageable pageable) {
    //      return  emailRepository.findAll(pageable);
    //  }

    //  public Optional<EmailDetails> findById(long emailId) {
    //      return emailRepository.findById(emailId);
    //  }
}
