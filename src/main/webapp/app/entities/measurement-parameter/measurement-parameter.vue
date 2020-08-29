<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kiptenderApp.measurementParameter.home.title')" id="measurement-parameter-heading">Measurement Parameters</span>
            <router-link :to="{name: 'MeasurementParameterCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-measurement-parameter">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kiptenderApp.measurementParameter.home.createLabel')">
                    Create a new Measurement Parameter
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && measurementParameters && measurementParameters.length === 0">
            <span v-text="$t('kiptenderApp.measurementParameter.home.notFound')">No measurementParameters found</span>
        </div>
        <div class="table-responsive" v-if="measurementParameters && measurementParameters.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('title')"><span v-text="$t('kiptenderApp.measurementParameter.title')">Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="measurementParameter in measurementParameters"
                    :key="measurementParameter.id">
                    <td>
                        <router-link :to="{name: 'MeasurementParameterView', params: {measurementParameterId: measurementParameter.id}}">{{measurementParameter.id}}</router-link>
                    </td>
                    <td>{{measurementParameter.title}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MeasurementParameterView', params: {measurementParameterId: measurementParameter.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MeasurementParameterEdit', params: {measurementParameterId: measurementParameter.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(measurementParameter)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="kiptenderApp.measurementParameter.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-measurementParameter-heading" v-text="$t('kiptenderApp.measurementParameter.delete.question', {'id': removeId})">Are you sure you want to delete this Measurement Parameter?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-measurementParameter" v-text="$t('entity.action.delete')" v-on:click="removeMeasurementParameter()">Delete</button>
            </div>
        </b-modal>
        <div v-show="measurementParameters && measurementParameters.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./measurement-parameter.component.ts">
</script>
