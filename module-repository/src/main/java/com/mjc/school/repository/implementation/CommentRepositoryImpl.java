package com.mjc.school.repository.implementation;

import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.model.CommentModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
		@PersistenceUnit
		private EntityManagerFactory entityManagerFactory;
		@Override
		public List<CommentModel> readAll() {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				TypedQuery<CommentModel> query = entityManager.createQuery("select c from CommentModel c", CommentModel.class);
				List<CommentModel> commentModelList = query.getResultList();
				entityManager.close();
				return commentModelList;
		}

		@Override
		public Optional<CommentModel> readById(Long id) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				Optional<CommentModel> commentById =
								entityManager.createQuery("select c from CommentModel c where c.id = ?1", CommentModel.class)
												.setParameter(1, id)
												.getResultStream()
												.findFirst();
				entityManager.close();
				return commentById;
		}

		@Override
		public CommentModel create(CommentModel entity) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try {
						entityManager.persist(entity);
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
				}
				entityManager.close();
				return entity;
		}

		@Override
		public CommentModel update(CommentModel entity) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try {
						entityManager.merge(entity);
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
				}
				entityManager.close();
				return entity;
		}

		@Override
		public boolean deleteById(Long id) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try{
						entityManager.createQuery("delete from CommentModel where id = ?1")
										.setParameter( 1, id )
										.executeUpdate();
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
						entityManager.close();
						return Boolean.FALSE;
				}
				entityManager.close();
				return Boolean.TRUE;
		}

		@Override
		public boolean existById(Long id) {
				return readById(id).isPresent();
		}

		@Override
		public List<CommentModel> readByNewsId(Long newsId) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				List<CommentModel> readByNewsIdList = entityManager
								.createQuery("select c from CommentModel c join FETCH c.newsModel n where n.id =?1", CommentModel.class)
								.setParameter(1, newsId)
								.getResultList();
				entityManager.close();
				return readByNewsIdList;
		}
}
