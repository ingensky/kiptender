<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.measurementParameter.home.createOrEditLabel" v-text="$t('kiptenderApp.measurementParameter.home.createOrEditLabel')">Create or edit a MeasurementParameter</h2>
                <div>
                    <div class="form-group" v-if="measurementParameter.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="measurementParameter.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.measurementParameter.title')" for="measurement-parameter-title">Title</label>
                        <input type="text" class="form-control" name="title" id="measurement-parameter-title"
                            :class="{'valid': !$v.measurementParameter.title.$invalid, 'invalid': $v.measurementParameter.title.$invalid }" v-model="$v.measurementParameter.title.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.measurementParameter.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./measurement-parameter-update.component.ts">
</script>
