<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kiptenderApp.kIPWiringDiagramType.home.title')" id="kip-wiring-diagram-type-heading">KIP Wiring Diagram Types</span>
            <router-link :to="{name: 'KIPWiringDiagramTypeCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-kip-wiring-diagram-type">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kiptenderApp.kIPWiringDiagramType.home.createLabel')">
                    Create a new KIP Wiring Diagram Type
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
        <div class="alert alert-warning" v-if="!isFetching && kIPWiringDiagramTypes && kIPWiringDiagramTypes.length === 0">
            <span v-text="$t('kiptenderApp.kIPWiringDiagramType.home.notFound')">No kIPWiringDiagramTypes found</span>
        </div>
        <div class="table-responsive" v-if="kIPWiringDiagramTypes && kIPWiringDiagramTypes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('title')"><span v-text="$t('kiptenderApp.kIPWiringDiagramType.title')">Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('kiptenderApp.kIPWiringDiagramType.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="kIPWiringDiagramType in kIPWiringDiagramTypes"
                    :key="kIPWiringDiagramType.id">
                    <td>
                        <router-link :to="{name: 'KIPWiringDiagramTypeView', params: {kIPWiringDiagramTypeId: kIPWiringDiagramType.id}}">{{kIPWiringDiagramType.id}}</router-link>
                    </td>
                    <td>{{kIPWiringDiagramType.title}}</td>
                    <td>{{kIPWiringDiagramType.description}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'KIPWiringDiagramTypeView', params: {kIPWiringDiagramTypeId: kIPWiringDiagramType.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'KIPWiringDiagramTypeEdit', params: {kIPWiringDiagramTypeId: kIPWiringDiagramType.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(kIPWiringDiagramType)"
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
            <span slot="modal-title"><span id="kiptenderApp.kIPWiringDiagramType.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-kIPWiringDiagramType-heading" v-text="$t('kiptenderApp.kIPWiringDiagramType.delete.question', {'id': removeId})">Are you sure you want to delete this KIP Wiring Diagram Type?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-kIPWiringDiagramType" v-text="$t('entity.action.delete')" v-on:click="removeKIPWiringDiagramType()">Delete</button>
            </div>
        </b-modal>
        <div v-show="kIPWiringDiagramTypes && kIPWiringDiagramTypes.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./kip-wiring-diagram-type.component.ts">
</script>
