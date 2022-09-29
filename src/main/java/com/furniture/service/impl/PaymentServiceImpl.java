package com.furniture.service.impl;

import java.util.List;
import java.util.Optional;

import com.furniture.Repository.OrderRepository;
import com.furniture.model.Orders;
import com.furniture.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furniture.Repository.PaymentRepository;
import com.furniture.exception.PaymentNotFoundException;
import com.furniture.model.Payment;
import com.furniture.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Payment addPayment(Payment payment,int pid,int orderId) {
		Optional<Payment> p =(paymentRepo.findById(pid));
		Optional<Orders> o =(orderRepository.findById(orderId));
		if(p.isPresent()){
          Payment p1=p.get();
		  Orders orders=o.get();
          p1.setPaymentAmount(payment.getPaymentAmount());
          p1.setEmail((payment.getEmail()));
          p1.setMethod((payment.getMethod()));


		  orders.setStatus(true);

		  orderRepository.save(orders);

          return this.paymentRepo.save(p1);

		}
		else {
			throw new PaymentNotFoundException("Payment not found");
		}

	}

	@Override
	public Payment removePayment(long id) throws PaymentNotFoundException {

		return null;
	}

	@Override
	public boolean deletePaymentById(Integer paymentId) {
		this.paymentRepo.deleteById(paymentId);
		return true;
	}

	@Override
	public Payment getPaymentById(Integer paymentId) throws PaymentNotFoundException {
		Optional<Payment> paymentOpt = this.paymentRepo.findById(paymentId);
		if (paymentOpt.isEmpty())
			throw new PaymentNotFoundException("Id Not Found");

		return paymentOpt.get();
	}

	@Override
	public List<Payment> getAllPaymentDetails() {
		return this.paymentRepo.findAll();
	}


}
