package com.spring.pr.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	@Autowired
	private JavaMailSender mailSender;
	private int authNum;
	
	//���� �߻� (�����е� ����� �����ϼ���)
	public void makeRandomNumber() {
		//������ ����: 111111 ~ 999999
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("������ȣ: " + checkNum);
		authNum = checkNum;
	}

	
	//ȸ�� ���� �� ����� �̸��� ���
	public String joinEmail(String email) {
		makeRandomNumber();
		
		String setFrom = "stephen4951234@gmail.com"; //email-config�� ������ �ڽ��� �̸��� �ּҸ� �Է�.
		String toMail = email; //���Ź��� �̸���
		String title = "ȸ�� ���� ���� �̸��� �Դϴ�."; //�̸��� ����
		String content = "Ȩ�������� �湮�� �ּż� �����մϴ�." + 
						"<br><br>" + 
						"���� ��ȣ�� " + authNum + "�Դϴ�." + 
						"<br>" + 
						"�ش� ���� ��ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���."; //�̸��� ���� ����.
		
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNum); //������ ���ڿ��� �����ؼ� ����.
	}
	
	//�̸��� ���� �޼���
	public void mailSend(String setFrom, String toMail, String title, String content) {
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			//��Ÿ �������� ����� MimeMessageHelper ��ü�� ����.
			//�������� �Ű������� MimeMessage ��ü, bool, ���� ���ڵ� ����
			//true �Ű����� �����ϸ� MultiPart ������ �޼��� ������ ����.
			//���� �������� ������ �ܼ� �ؽ�Ʈ�� ���.
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			//true -> html �������� ����, �ƹ��͵� �ۼ����� ������ �ܼ� �ؽ�Ʈ.
			helper.setText(content, true);
			
			mailSender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}















