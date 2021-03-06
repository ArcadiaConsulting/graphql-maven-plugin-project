package com.graphql_java_generator.client.domain.allGraphQLCases;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLInterfaceType;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLScalar;

import java.util.Date;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "__typename", visible = true)
		@JsonSubTypes({ @Type(value = Human.class, name = "Human") })
		@GraphQLInterfaceType("Commented")
public interface Commented  {

	@GraphQLScalar(fieldName = "nbComments", graphQLTypeName = "Int", javaClass = Integer.class)
	public void setNbComments(Integer nbComments);

	@GraphQLScalar(fieldName = "nbComments", graphQLTypeName = "Int", javaClass = Integer.class)
	public Integer getNbComments();

	@JsonDeserialize(contentAs = String.class)
	@GraphQLScalar(fieldName = "comments", graphQLTypeName = "String", javaClass = String.class)
	public void setComments(List<String> comments);

	@JsonDeserialize(contentAs = String.class)
	@GraphQLScalar(fieldName = "comments", graphQLTypeName = "String", javaClass = String.class)
	public List<String> getComments();

	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	public void set__typename(String __typename);

	@GraphQLScalar(fieldName = "__typename", graphQLTypeName = "String", javaClass = String.class)
	public String get__typename();
}
