package com.centroinformacion.kafka.config.service;

import java.text.SimpleDateFormat;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.centroinformacion.entity.Ejemplo;
import com.centroinformacion.kafka.config.Event;
import com.centroinformacion.kafka.entity.EjemploCreateEvent;

@Component
public class EjemploEventListener {



	
	@KafkaListener(topics = "${topic.customer.name:topic-ejemplo-Jacinto-G1}",
				  containerFactory = "kafkaListenerContainerFactory",
				  groupId = "escuchador-Ejemplo")
	public void consumer(Event<?> event) {
		System.out.println("1 Evento recibido  " + event);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if(event.getClass().isAssignableFrom(EjemploCreateEvent.class)) {
			System.out.println("2 Evento Ejemplo  " + event);
			EjemploCreateEvent ejemploEvent = (EjemploCreateEvent) event;
			
			String id = ejemploEvent.getId();
			String fecha = sdf.format(ejemploEvent.getDate());
			String tipo = ejemploEvent.getType().toString();
			Ejemplo objEjemplo =  ejemploEvent.getData();
			String descripcion = objEjemplo.getDescripcion();
					
			System.out.println("3 ID: " + id);
			System.out.println("4 Fecha: " + fecha);
			System.out.println("5 Tipo: " + tipo);
			System.out.println("6 descripcion: " + descripcion);
		}
		
	}
}
