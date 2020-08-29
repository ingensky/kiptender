<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kiptenderApp.projectMark.home.title')" id="project-mark-heading">Project Marks</span>
            <router-link :to="{name: 'ProjectMarkCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-project-mark">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kiptenderApp.projectMark.home.createLabel')">
                    Create a new Project Mark
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
        <div class="alert alert-warning" v-if="!isFetching && projectMarks && projectMarks.length === 0">
            <span v-text="$t('kiptenderApp.projectMark.home.notFound')">No projectMarks found</span>
        </div>
        <div class="table-responsive" v-if="projectMarks && projectMarks.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('title')"><span v-text="$t('kiptenderApp.projectMark.title')">Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mark')"><span v-text="$t('kiptenderApp.projectMark.mark')">Mark</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mark'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="projectMark in projectMarks"
                    :key="projectMark.id">
                    <td>
                        <router-link :to="{name: 'ProjectMarkView', params: {projectMarkId: projectMark.id}}">{{projectMark.id}}</router-link>
                    </td>
                    <td>{{projectMark.title}}</td>
                    <td>{{projectMark.mark}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProjectMarkView', params: {projectMarkId: projectMark.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ProjectMarkEdit', params: {projectMarkId: projectMark.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(projectMark)"
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
            <span slot="modal-title"><span id="kiptenderApp.projectMark.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-projectMark-heading" v-text="$t('kiptenderApp.projectMark.delete.question', {'id': removeId})">Are you sure you want to delete this Project Mark?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-projectMark" v-text="$t('entity.action.delete')" v-on:click="removeProjectMark()">Delete</button>
            </div>
        </b-modal>
        <div v-show="projectMarks && projectMarks.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./project-mark.component.ts">
</script>
