<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.projectMark.home.createOrEditLabel" v-text="$t('kiptenderApp.projectMark.home.createOrEditLabel')">Create or edit a ProjectMark</h2>
                <div>
                    <div class="form-group" v-if="projectMark.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="projectMark.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.projectMark.title')" for="project-mark-title">Title</label>
                        <input type="text" class="form-control" name="title" id="project-mark-title"
                            :class="{'valid': !$v.projectMark.title.$invalid, 'invalid': $v.projectMark.title.$invalid }" v-model="$v.projectMark.title.$model"  required/>
                        <div v-if="$v.projectMark.title.$anyDirty && $v.projectMark.title.$invalid">
                            <small class="form-text text-danger" v-if="!$v.projectMark.title.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.projectMark.title.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.projectMark.mark')" for="project-mark-mark">Mark</label>
                        <input type="text" class="form-control" name="mark" id="project-mark-mark"
                            :class="{'valid': !$v.projectMark.mark.$invalid, 'invalid': $v.projectMark.mark.$invalid }" v-model="$v.projectMark.mark.$model"  required/>
                        <div v-if="$v.projectMark.mark.$anyDirty && $v.projectMark.mark.$invalid">
                            <small class="form-text text-danger" v-if="!$v.projectMark.mark.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.projectMark.mark.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.projectMark.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./project-mark-update.component.ts">
</script>
