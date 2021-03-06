package com.graphql_java_generator.client.domain.allGraphQLCases;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLInputType;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLObjectType;
import com.graphql_java_generator.annotation.GraphQLQuery;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.annotation.RequestType;

import java.util.Date;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLObjectType("__InputValue")
public class __InputValue  {

	@JsonProperty("name")
	@GraphQLScalar(fieldName = "name", graphQLTypeName = "String", javaClass = String.class)
	String name;


	@JsonProperty("description")
	@GraphQLScalar(fieldName = "description", graphQLTypeName = "String", javaClass = String.class)
	String description;


	@JsonProperty("type")
	@GraphQLNonScalar(fieldName = "type", graphQLTypeName = "__Type", javaClass = __Type.class)
	__Type type;


	@JsonProperty("defaultValue")
	@GraphQLScalar(fieldName = "defaultValue", graphQLTypeName = "String", javaClass = String.class)
	String defaultValue;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	String __typename;



	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setType(__Type type) {
		this.type = type;
	}

	public __Type getType() {
		return type;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "__InputValue {"
				+ "name: " + name
				+ ", "
				+ "description: " + description
				+ ", "
				+ "type: " + type
				+ ", "
				+ "defaultValue: " + defaultValue
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Name("name"),
		Description("description"),
		Type("type"),
		DefaultValue("defaultValue"),
		__typename("__typename");

		private String fieldName;

		Field(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Class<?> getGraphQLType() {
			return this.getClass().getDeclaringClass();
		}

	}

	public static Builder builder() {
			return new Builder();
		}



	/**
	 * Builder
	 */
	public static class Builder {
		private String name;
		private String description;
		private __Type type;
		private String defaultValue;


		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder withType(__Type type) {
			this.type = type;
			return this;
		}
		public Builder withDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
			return this;
		}

		public __InputValue build() {
			__InputValue object = new __InputValue();
			object.setName(name);
			object.setDescription(description);
			object.setType(type);
			object.setDefaultValue(defaultValue);
			object.set__typename("__InputValue");
			return object;
		}
	}
}
