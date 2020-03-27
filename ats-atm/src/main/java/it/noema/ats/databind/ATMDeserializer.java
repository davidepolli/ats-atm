package it.noema.ats.databind;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import it.noema.ats.model.ATM;

/**
 * deserializer fot the ATM Entity
 * @author Noema STI
 *
 */
public class ATMDeserializer extends StdDeserializer<ATM> {

	public ATMDeserializer() {
		this(null);
	}
	protected ATMDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ATM deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		JsonNode atmNode = jp.getCodec().readTree(jp);
		ATM atm = new ATM();
		atm.setStreet(atmNode.get("address").get("street").textValue());
		atm.setHousenumber(atmNode.get("address").get("housenumber").textValue());
		atm.setPostalcode(atmNode.get("address").get("postalcode").textValue());
		atm.setCity(atmNode.get("address").get("city").textValue());
		atm.setType(atmNode.get("type").textValue());
		
		JsonNode lng = atmNode.get("address").get("geoLocation").get("lng");
		
		if (lng != null) {
			atm.setLng(lng.textValue());
		}
		
		JsonNode lat = atmNode.get("address").get("geoLocation").get("lat");
		if (lat != null) {
			atm.setLat(lat.textValue());
		}
		
		
		return atm;
	}

}

