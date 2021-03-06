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
@GraphQLQuery(name = "AnotherMutationType", type = RequestType.mutation)
public class AnotherMutationTypeResponse  {

	@GraphQLInputParameters(names = {"human"}, types = {"HumanInput"})
	@JsonProperty("createHuman")
	@GraphQLNonScalar(fieldName = "createHuman", graphQLTypeName = "Human", javaClass = Human.class)
	Human createHuman;


	@GraphQLInputParameters(names = {"input"}, types = {"AllFieldCasesInput"})
	@JsonProperty("createAllFieldCases")
	@GraphQLNonScalar(fieldName = "createAllFieldCases", graphQLTypeName = "AllFieldCases", javaClass = AllFieldCases.class)
	AllFieldCases createAllFieldCases;



	public void setCreateHuman(Human createHuman) {
		this.createHuman = createHuman;
	}

	public Human getCreateHuman() {
		return createHuman;
	}

	public void setCreateAllFieldCases(AllFieldCases createAllFieldCases) {
		this.createAllFieldCases = createAllFieldCases;
	}

	public AllFieldCases getCreateAllFieldCases() {
		return createAllFieldCases;
	}

    public String toString() {
        return "AnotherMutationType {"
				+ "createHuman: " + createHuman
				+ ", "
				+ "createAllFieldCases: " + createAllFieldCases
        		+ "}";
    }

    /**
	 * Enum of field names
	 */
	 public static enum Field implements GraphQLField {
		CreateHuman("createHuman"),
		CreateAllFieldCases("createAllFieldCases");

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
		private Human createHuman;
		private AllFieldCases createAllFieldCases;


		public Builder withCreateHuman(Human createHuman) {
			this.createHuman = createHuman;
			return this;
		}
		public Builder withCreateAllFieldCases(AllFieldCases createAllFieldCases) {
			this.createAllFieldCases = createAllFieldCases;
			return this;
		}

		public AnotherMutationTypeResponse build() {
			AnotherMutationTypeResponse object = new AnotherMutationTypeResponse();
			object.setCreateHuman(createHuman);
			object.setCreateAllFieldCases(createAllFieldCases);
			return object;
		}
	}
}
