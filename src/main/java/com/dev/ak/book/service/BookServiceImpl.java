package com.dev.ak.book.service;

import com.dev.ak.book.model.Book;
import com.dev.ak.book.repository.BookRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public void addBook(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public List<Book> findAll() {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Book findOne(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? book.get() : null;
    }

    @Override
    public ExecutionResult getFilteredBook(String query) {

        System.out.println("query "+ query);
        ExecutionResult result = graphQL.execute(query);
        return result;
    }

    // graphql

    GraphQL graphQL;

    @Value("classpath:graphql/schema.graphqls")
    private Resource schemaResource;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Book>> findAllFetcher = data -> {
            return (List<Book>) bookRepository.findAll();  // fatch all value and return needs fields
        };

        DataFetcher<List<Book>> findByRatingFetcher = data -> {
            return (List<Book>) bookRepository.findByRating(data.getArgument("rating")); // read parameter name email and fetch value
        };

        DataFetcher<List<Book>> findByNameFetcher = data -> {
            return (List<Book>)bookRepository.findByNameContaining(data.getArgument("name")); // read parameter name email and fetch value
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                        typeWriting -> typeWriting.dataFetcher("allBooks", findAllFetcher)
                                .dataFetcher("findBookByRating", findByRatingFetcher)
                                .dataFetcher("findBookByName", findByNameFetcher)
                )

                .build();

    }


}
