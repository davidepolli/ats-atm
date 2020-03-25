package it.noema.ats.config.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.noema.ats.model.ATM;

public class ATM2ResourceReader extends Jackson2ResourceReader {
	
	public ATM2ResourceReader(ObjectMapper mapper) {
		super(mapper);
		this.mapper = mapper;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.init.ResourceReader#readFrom(org.springframework.core.io.Resource, java.lang.ClassLoader)
	 */
	public Object readFrom(Resource resource, ClassLoader classLoader) throws Exception {

		InputStream stream = resource.getInputStream();
		JsonNode node = mapper.reader(JsonNode.class).readTree(stream);

		if (node.isArray()) {

			Iterator<JsonNode> elements = node.elements();
			List<Object> result = new ArrayList<Object>();

			while (elements.hasNext()) {
				JsonNode element = elements.next();
				result.add(readSingle(element, classLoader));
			}

			return result;
		}

		return readSingle(node, classLoader);
	}
	
	/**
	 * Reads the given {@link JsonNode} into an instance of the type encoded in it using the configured type key.
	 * 
	 * @param node must not be {@literal null}.
	 * @param classLoader
	 * @return
	 */
	private Object readSingle(JsonNode node, ClassLoader classLoader) throws IOException {

		Class<?> type = ClassUtils.resolveClassName(ATM.class.getName(), classLoader);

		return mapper.reader(type).readValue(node);
	}
	
	private ObjectMapper mapper;

	
}
