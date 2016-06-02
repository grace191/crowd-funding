package com.incubator.springmvc.controller;

import java.util.UUID;

public class UUIDEditor extends java.beans.PropertyEditorSupport {

	@Override
	public Object getValue() {
		Object o = super.getValue();
		if (o != null && o instanceof String[]) {
			String[] input = (String[]) o;
			UUID[] result = new UUID[input.length];
			for (int i = 0, inputLength = input.length; i < inputLength; i++) {
				result[i] = UUID.fromString(input[i]);
			}
			return result;
		}
		return o;
	}
}
