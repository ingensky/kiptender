<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kiptenderApp.tender.home.title')" id="tender-heading">Tenders</span>
            <router-link :to="{name: 'TenderCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-tender">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kiptenderApp.tender.home.createLabel')">
                    Create a new Tender
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
        <div class="alert alert-warning" v-if="!isFetching && tenders && tenders.length === 0">
            <span v-text="$t('kiptenderApp.tender.home.notFound')">No tenders found</span>
        </div>
        <div class="table-responsive" v-if="tenders && tenders.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('number')"><span v-text="$t('kiptenderApp.tender.number')">Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'number'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('title')"><span v-text="$t('kiptenderApp.tender.title')">Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('kiptenderApp.tender.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('location')"><span v-text="$t('kiptenderApp.tender.location')">Location</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'location'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('worktrip')"><span v-text="$t('kiptenderApp.tender.worktrip')">Worktrip</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'worktrip'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('needSIDStage')"><span v-text="$t('kiptenderApp.tender.needSIDStage')">Need SID Stage</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'needSIDStage'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('needOTRStage')"><span v-text="$t('kiptenderApp.tender.needOTRStage')">Need OTR Stage</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'needOTRStage'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('client.id')"><span v-text="$t('kiptenderApp.tender.client')">Client</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'client.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="tender in tenders"
                    :key="tender.id">
                    <td>
                        <router-link :to="{name: 'TenderView', params: {tenderId: tender.id}}">{{tender.id}}</router-link>
                    </td>
                    <td>{{tender.number}}</td>
                    <td>{{tender.title}}</td>
                    <td>{{tender.description}}</td>
                    <td>{{tender.location}}</td>
                    <td>{{tender.worktrip}}</td>
                    <td>{{tender.needSIDStage}}</td>
                    <td>{{tender.needOTRStage}}</td>
                    <td>
                        <div v-if="tender.client">
                            <router-link :to="{name: 'ClientView', params: {clientId: tender.client.id}}">{{tender.client.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TenderView', params: {tenderId: tender.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'TenderEdit', params: {tenderId: tender.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(tender)"
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
            <span slot="modal-title"><span id="kiptenderApp.tender.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-tender-heading" v-text="$t('kiptenderApp.tender.delete.question', {'id': removeId})">Are you sure you want to delete this Tender?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-tender" v-text="$t('entity.action.delete')" v-on:click="removeTender()">Delete</button>
            </div>
        </b-modal>
        <div v-show="tenders && tenders.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./tender.component.ts">
</script>
