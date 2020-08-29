<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.kIPWiringDiagramType.home.createOrEditLabel" v-text="$t('kiptenderApp.kIPWiringDiagramType.home.createOrEditLabel')">Create or edit a KIPWiringDiagramType</h2>
                <div>
                    <div class="form-group" v-if="kIPWiringDiagramType.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="kIPWiringDiagramType.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.kIPWiringDiagramType.title')" for="kip-wiring-diagram-type-title">Title</label>
                        <input type="text" class="form-control" name="title" id="kip-wiring-diagram-type-title"
                            :class="{'valid': !$v.kIPWiringDiagramType.title.$invalid, 'invalid': $v.kIPWiringDiagramType.title.$invalid }" v-model="$v.kIPWiringDiagramType.title.$model"  required/>
                        <div v-if="$v.kIPWiringDiagramType.title.$anyDirty && $v.kIPWiringDiagramType.title.$invalid">
                            <small class="form-text text-danger" v-if="!$v.kIPWiringDiagramType.title.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.kIPWiringDiagramType.title.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.kIPWiringDiagramType.description')" for="kip-wiring-diagram-type-description">Description</label>
                        <input type="text" class="form-control" name="description" id="kip-wiring-diagram-type-description"
                            :class="{'valid': !$v.kIPWiringDiagramType.description.$invalid, 'invalid': $v.kIPWiringDiagramType.description.$invalid }" v-model="$v.kIPWiringDiagramType.description.$model" />
                        <div v-if="$v.kIPWiringDiagramType.description.$anyDirty && $v.kIPWiringDiagramType.description.$invalid">
                            <small class="form-text text-danger" v-if="!$v.kIPWiringDiagramType.description.minLength" v-text="$t('entity.validation.minlength', {min: 3})">
                                This field is required to be at least 3 characters.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.kIPWiringDiagramType.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./kip-wiring-diagram-type-update.component.ts">
</script>
