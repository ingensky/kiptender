<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kiptenderApp.unitKIPiAGroup.home.title')" id="unit-ki-pi-a-group-heading">Unit KI Pi A Groups</span>
            <router-link :to="{name: 'UnitKIPiAGroupCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-unit-ki-pi-a-group">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kiptenderApp.unitKIPiAGroup.home.createLabel')">
                    Create a new Unit KI Pi A Group
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
        <div class="alert alert-warning" v-if="!isFetching && unitKIPiAGroups && unitKIPiAGroups.length === 0">
            <span v-text="$t('kiptenderApp.unitKIPiAGroup.home.notFound')">No unitKIPiAGroups found</span>
        </div>
        <div class="table-responsive" v-if="unitKIPiAGroups && unitKIPiAGroups.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('quantity')"><span v-text="$t('kiptenderApp.unitKIPiAGroup.quantity')">Quantity</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'quantity'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="unitKIPiAGroup in unitKIPiAGroups"
                    :key="unitKIPiAGroup.id">
                    <td>
                        <router-link :to="{name: 'UnitKIPiAGroupView', params: {unitKIPiAGroupId: unitKIPiAGroup.id}}">{{unitKIPiAGroup.id}}</router-link>
                    </td>
                    <td>{{unitKIPiAGroup.quantity}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'UnitKIPiAGroupView', params: {unitKIPiAGroupId: unitKIPiAGroup.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'UnitKIPiAGroupEdit', params: {unitKIPiAGroupId: unitKIPiAGroup.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(unitKIPiAGroup)"
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
            <span slot="modal-title"><span id="kiptenderApp.unitKIPiAGroup.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-unitKIPiAGroup-heading" v-text="$t('kiptenderApp.unitKIPiAGroup.delete.question', {'id': removeId})">Are you sure you want to delete this Unit KI Pi A Group?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-unitKIPiAGroup" v-text="$t('entity.action.delete')" v-on:click="removeUnitKIPiAGroup()">Delete</button>
            </div>
        </b-modal>
        <div v-show="unitKIPiAGroups && unitKIPiAGroups.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unit-ki-pi-a-group.component.ts">
</script>
