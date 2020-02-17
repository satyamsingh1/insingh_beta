package com.mps.insight.kafka;

import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mps.insight.global.InsightConstant;

public class KafkaService {

	private static final Logger log = LoggerFactory.getLogger(KafkaService.class);
	private Producer<Long, String> producer = null;

	public boolean sendLog(Document document) {

		try {

			producer = ProducerCreator.createProducer();
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(InsightConstant.LOG_TOPIC_NAME,
					document.toJson());
			Future<RecordMetadata> metadata = producer.send(record);
			while (!metadata.isDone()) {
			}
			return true;
		} catch (Exception e) {
			log.error("sendMessage : Exception : " + e.toString() + " : " + document.toJson());
			return false;
		}
	}

	public boolean sendFeed(Document document) {

		try {

			producer = ProducerCreator.createProducer();
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(InsightConstant.FEED_TOPIC_NAME,
					document.toJson());
			Future<RecordMetadata> metadata = producer.send(record);
			while (!metadata.isDone()) {
			}
			return true;
		} catch (Exception e) {
			log.error("sendMessage : Exception : " + e.toString() + " : " + document.toJson());
			return false;
		}
	}

	public void disconnect() {
		// closing producer
		if (producer != null) {
			try {
				producer.flush();
			} catch (Exception e) {
			}
			;
			try {
				producer.close();
			} catch (Exception e) {
			}
			;
		}
	}
}
