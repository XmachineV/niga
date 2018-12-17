package com.denis.niga.web;

import com.denis.niga.domain.Model;
import com.denis.niga.service.AbstractService;
import com.denis.niga.web.errors.BadRequestAlertException;
import com.denis.niga.web.util.HeaderUtil;
import com.denis.niga.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


public abstract class AbstractResource<T extends Model, ID> {

    private final AbstractService<T, ID> service;
    private Logger log;


    public AbstractResource(AbstractService service) {
        this.service = service;
        log = LoggerFactory.getLogger(getEntityClass());
    }

    public abstract Class<T> getEntityClass();
    public abstract String pathParem();

    protected String getModelName() {
        return this.getEntityClass().getSimpleName().substring(0, 1).toLowerCase() + this.getEntityClass().getSimpleName().substring(1);
    }

    /**
     * POST  / : Create a new model.
     *
     * @param model the model to create
     * @return the ResponseEntity with status 201 (Created) and with body the new model, or with status 400 (Bad Request) if the model has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    public ResponseEntity<T> create(T model) throws URISyntaxException {
        log.debug("REST request to save " + getModelName() + ": {}", model);
        if (model.getId() != null) {
            throw new BadRequestAlertException("Um novo modelo não deve possuir ID", getModelName(), "idexists");
        }
        T result = service.save(model);
        return ResponseEntity.created(new URI("/api/" + pathParem() + "/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(getModelName(), result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /jobs : Updates an existing job.
     *
     * @param model the model to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated model,
     * or with status 400 (Bad Request) if the model is not valid,
     * or with status 500 (Internal Server Error) if the model couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    public ResponseEntity<T> update(T model) throws URISyntaxException {
        log.debug("REST request to update " + getModelName() + ": {}", model);
        if (model.getId() == null) {
            throw new BadRequestAlertException("Invalid id", getModelName(), "idnull");
        }
        T result = service.save(model);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(getModelName(), model.getId().toString()))
                .body(result);
    }

    /**
     * GET  /models : get all the models.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of models in body
     */

    public ResponseEntity<List<T>> findAll(Pageable pageable) {
        log.debug("REST request to get a page of models");

        Page<T> page = service.findAll(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/" + pathParem());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /models/:id : get the "id" model.
     *
     * @param id the id of the model to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the model, or with status 404 (Not Found)
     */
    public ResponseEntity<T> findById(ID id) {
        log.debug("REST request to get model : {}", id);
        Optional<T> model = service.findById(id);
        return ResponseUtil.wrapOrNotFound(model);


    }

    /**
     * DELETE  /models/:id : delete the "id" model.
     *
     * @param id the id of the model to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    public ResponseEntity<Void> delete(ID id) {
        log.debug("REST request to delete model : {}", id);
        service.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(getModelName(), id.toString())).build();
    }
}


