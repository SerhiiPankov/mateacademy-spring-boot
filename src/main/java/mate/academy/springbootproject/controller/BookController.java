package mate.academy.springbootproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.BookDto;
import mate.academy.springbootproject.dto.CreateBookRequestDto;
import mate.academy.springbootproject.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get list of all available books")
    @ApiResponse(responseCode = "200", description = "All books",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookDto.class)))})
    @Parameter(name = "page", description = "Page number to search",
            schema = @Schema(implementation = Integer.class))
    @Parameter(name = "size", description = "Number of books per page",
            schema = @Schema(implementation = Integer.class))
    @Parameter(name = "sort",
            description = "Possible fields for sorting: 'title', 'author', 'price'",
            schema = @Schema(implementation = String.class))
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "Get book by Id", description = "Get book by Id")
    @ApiResponse(responseCode = "200", description = "Book by Id",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookDto.class)))})
    public BookDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    @Operation(summary = "Create new book", description = "Create new book")
    @ApiResponse(responseCode = "200", description = "Create new book",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookDto.class)))})
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }

    @PutMapping("/{bookId}")
    @Operation(summary = "Update book", description = "Update book")
    @ApiResponse(responseCode = "200", description = "Update book",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookDto.class)))})
    public BookDto updateBook(@PathVariable Long bookId,
                              @RequestBody CreateBookRequestDto bookRequestDto) {
        return bookService.updateBook(bookId, bookRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{bookId}")
    @Operation(summary = "Delete book by Id", description = "Delete book by Id")
    @ApiResponse(responseCode = "200", description = "Delete book")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    @GetMapping("/search")
    @Operation(summary = "Get all books", description = "Get list of all available books")
    @ApiResponse(responseCode = "200", description = "All books",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookDto.class)))})
    @Parameters(value = {
            @Parameter(name = "page",
                    description = "Page number to search",
            schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "size",
                    description = "Number of books per page",
                    schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "sort",
                    description = "Possible fields for sorting: 'title', 'author', 'price'",
                    schema = @Schema(implementation = String.class)),
            @Parameter(name = "'title' - additional properties",
                    description = "Search book by title",
                    schema = @Schema(implementation = String[].class)),
            @Parameter(name = "'author' - additional properties",
                    description = "Search book by author",
                    schema = @Schema(implementation = String[].class))})
    public Page<BookDto> searchBooks(Pageable pageable, @RequestBody Map<String, String[]> params) {
        return bookService.search(pageable, params);
    }
}
