<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.client.home.createOrEditLabel" v-text="$t('kiptenderApp.client.home.createOrEditLabel')">Create or edit a Client</h2>
                <div>
                    <div class="form-group" v-if="client.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="client.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.client.title')" for="client-title">Title</label>
                        <input type="text" class="form-control" name="title" id="client-title"
                            :class="{'valid': !$v.client.title.$invalid, 'invalid': $v.client.title.$invalid }" v-model="$v.client.title.$model"  required/>
                        <div v-if="$v.client.title.$anyDirty && $v.client.title.$invalid">
                            <small class="form-text text-danger" v-if="!$v.client.title.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.client.title.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.client.contactName')" for="client-contactName">Contact Name</label>
                        <input type="text" class="form-control" name="contactName" id="client-contactName"
                            :class="{'valid': !$v.client.contactName.$invalid, 'invalid': $v.client.contactName.$invalid }" v-model="$v.client.contactName.$model" />
                        <div v-if="$v.client.contactName.$anyDirty && $v.client.contactName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.client.contactName.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.client.contactNumber')" for="client-contactNumber">Contact Number</label>
                        <input type="text" class="form-control" name="contactNumber" id="client-contactNumber"
                            :class="{'valid': !$v.client.contactNumber.$invalid, 'invalid': $v.client.contactNumber.$invalid }" v-model="$v.client.contactNumber.$model" />
                        <div v-if="$v.client.contactNumber.$anyDirty && $v.client.contactNumber.$invalid">
                            <small class="form-text text-danger" v-if="!$v.client.contactNumber.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.client.description')" for="client-description">Description</label>
                        <input type="text" class="form-control" name="description" id="client-description"
                            :class="{'valid': !$v.client.description.$invalid, 'invalid': $v.client.description.$invalid }" v-model="$v.client.description.$model" />
                        <div v-if="$v.client.description.$anyDirty && $v.client.description.$invalid">
                            <small class="form-text text-danger" v-if="!$v.client.description.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.client.user')" for="client-user">User</label>
                        <select class="form-control" id="client-user" name="user" v-model="client.user">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="client.user && userOption.id === client.user.id ? client.user : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.client.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./client-update.component.ts">
</script>
