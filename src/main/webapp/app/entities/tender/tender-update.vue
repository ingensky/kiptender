<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.tender.home.createOrEditLabel" v-text="$t('kiptenderApp.tender.home.createOrEditLabel')">Create or edit a Tender</h2>
                <div>
                    <div class="form-group" v-if="tender.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="tender.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.number')" for="tender-number">Number</label>
                        <input type="text" class="form-control" name="number" id="tender-number"
                            :class="{'valid': !$v.tender.number.$invalid, 'invalid': $v.tender.number.$invalid }" v-model="$v.tender.number.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.title')" for="tender-title">Title</label>
                        <input type="text" class="form-control" name="title" id="tender-title"
                            :class="{'valid': !$v.tender.title.$invalid, 'invalid': $v.tender.title.$invalid }" v-model="$v.tender.title.$model"  required/>
                        <div v-if="$v.tender.title.$anyDirty && $v.tender.title.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tender.title.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.tender.title.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.description')" for="tender-description">Description</label>
                        <input type="text" class="form-control" name="description" id="tender-description"
                            :class="{'valid': !$v.tender.description.$invalid, 'invalid': $v.tender.description.$invalid }" v-model="$v.tender.description.$model" />
                        <div v-if="$v.tender.description.$anyDirty && $v.tender.description.$invalid">
                            <small class="form-text text-danger" v-if="!$v.tender.description.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.location')" for="tender-location">Location</label>
                        <input type="text" class="form-control" name="location" id="tender-location"
                            :class="{'valid': !$v.tender.location.$invalid, 'invalid': $v.tender.location.$invalid }" v-model="$v.tender.location.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.worktrip')" for="tender-worktrip">Worktrip</label>
                        <input type="checkbox" class="form-check" name="worktrip" id="tender-worktrip"
                            :class="{'valid': !$v.tender.worktrip.$invalid, 'invalid': $v.tender.worktrip.$invalid }" v-model="$v.tender.worktrip.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.needSIDStage')" for="tender-needSIDStage">Need SID Stage</label>
                        <input type="checkbox" class="form-check" name="needSIDStage" id="tender-needSIDStage"
                            :class="{'valid': !$v.tender.needSIDStage.$invalid, 'invalid': $v.tender.needSIDStage.$invalid }" v-model="$v.tender.needSIDStage.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.needOTRStage')" for="tender-needOTRStage">Need OTR Stage</label>
                        <input type="checkbox" class="form-check" name="needOTRStage" id="tender-needOTRStage"
                            :class="{'valid': !$v.tender.needOTRStage.$invalid, 'invalid': $v.tender.needOTRStage.$invalid }" v-model="$v.tender.needOTRStage.$model" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('kiptenderApp.tender.projectMark')" for="tender-projectMark">Project Mark</label>
                        <select class="form-control" id="tender-projectMark" multiple name="projectMark" v-model="tender.projectMarks">
                            <option v-bind:value="getSelected(tender.projectMarks, projectMarkOption)" v-for="projectMarkOption in projectMarks" :key="projectMarkOption.id">{{projectMarkOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label v-text="$t('kiptenderApp.tender.unitKIPiAGroup')" for="tender-unitKIPiAGroup">Unit KI Pi A Group</label>
                        <select class="form-control" id="tender-unitKIPiAGroup" multiple name="unitKIPiAGroup" v-model="tender.unitKIPiAGroups">
                            <option v-bind:value="getSelected(tender.unitKIPiAGroups, unitKIPiAGroupOption)" v-for="unitKIPiAGroupOption in unitKIPiAGroups" :key="unitKIPiAGroupOption.id">{{unitKIPiAGroupOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label v-text="$t('kiptenderApp.tender.installationKIPiAGroup')" for="tender-installationKIPiAGroup">Installation KI Pi A Group</label>
                        <select class="form-control" id="tender-installationKIPiAGroup" multiple name="installationKIPiAGroup" v-model="tender.installationKIPiAGroups">
                            <option v-bind:value="getSelected(tender.installationKIPiAGroups, installationKIPiAGroupOption)" v-for="installationKIPiAGroupOption in installationKIPiAGroups" :key="installationKIPiAGroupOption.id">{{installationKIPiAGroupOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.tender.client')" for="tender-client">Client</label>
                        <select class="form-control" id="tender-client" name="client" v-model="tender.client">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="tender.client && clientOption.id === tender.client.id ? tender.client : clientOption" v-for="clientOption in clients" :key="clientOption.id">{{clientOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.tender.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./tender-update.component.ts">
</script>
