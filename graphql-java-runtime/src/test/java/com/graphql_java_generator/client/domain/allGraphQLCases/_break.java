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
@GraphQLObjectType("break")
public class _break  {

	@GraphQLInputParameters(names = {"test", "if"}, types = {"extends", "else"})
	@JsonProperty("case")
	@GraphQLScalar(fieldName = "case", graphQLTypeName = "extends", javaClass = _extends.class)
	_extends _case;


	@JsonProperty("__typename")
	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	String __typename;



	public void setCase(_extends _case) {
		this._case = _case;
	}

	public _extends getCase() {
		return _case;
	}

	public void set__typename(String __typename) {
		this.__typename = __typename;
	}

	public String get__typename() {
		return __typename;
	}

    public String toString() {
        return "_break {"
				+ "_case: " + _case
				+ ", "
				+ "__typename: " + __typename
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		Case("case"),
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
		private _extends _case;


		public Builder withCase(_extends _case) {
			this._case = _case;
			return this;
		}

		public _break build() {
			_break object = new _break();
			object.setCase(_case);
			object.set__typename("_break");
			return object;
		}
	}
}
