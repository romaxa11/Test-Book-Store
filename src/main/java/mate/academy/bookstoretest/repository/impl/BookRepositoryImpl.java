package mate.academy.bookstoretest.repository.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstoretest.exception.EntityNotFoundException;
import mate.academy.bookstoretest.model.Book;
import mate.academy.bookstoretest.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory factory;

    @Autowired
    public BookRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add"
                    + " book to DB: " + book.getTitle(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = factory.openSession()) {
            Query<Book> getAllProductsQuery = session.createQuery("FROM Book",Book.class);
            return getAllProductsQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find books ", e);
        }
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        try (Session session = factory.openSession()) {
            Book book = session.find(Book.class, id);
            return Optional.ofNullable(book);
        }
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        String lowerCaseAuthor = author.toLowerCase();
        try (Session session = factory.openSession()) {
            return session
                    .createQuery("SELECT b FROM Book b "
                            + "WHERE lower(b.author) LIKE :author", Book.class)
                    .setParameter("author","%" + lowerCaseAuthor + "%")
                    .getResultList();
        }
    }

    @Override
    public Book update(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("Could not update book with id "
                    + book.getId() + ". ");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete from Book where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("Could not remove book by id "
                    + id + ". ");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
