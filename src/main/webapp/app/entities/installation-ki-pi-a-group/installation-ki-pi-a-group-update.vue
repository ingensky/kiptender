<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.installationKIPiAGroup.home.createOrEditLabel" v-text="$t('kiptenderApp.installationKIPiAGroup.home.createOrEditLabel')">Create or edit a InstallationKIPiAGroup</h2>
                <div>
                    <div class="form-group" v-if="installationKIPiAGroup.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="installationKIPiAGroup.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.installationKIPiAGroup.quantity')" for="installation-ki-pi-a-group-quantity">Quantity</label>
                        <input type="number" class="form-control" name="quantity" id="installation-ki-pi-a-group-quantity"
                            :class="{'valid': !$v.installationKIPiAGroup.quantity.$invalid, 'invalid': $v.installationKIPiAGroup.quantity.$invalid }" v-model.number="$v.installationKIPiAGroup.quantity.$model" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('kiptenderApp.installationKIPiAGroup.installationKIPiA')" for="installation-ki-pi-a-group-installationKIPiA">Installation KI Pi A</label>
                        <select class="form-control" id="installation-ki-pi-a-group-installationKIPiA" multiple name="installationKIPiA" v-model="installationKIPiAGroup.installationKIPiAS">
                            <option v-bind:value="getSelected(installationKIPiAGroup.installationKIPiAS, installationKIPiAOption)" v-for="installationKIPiAOption in installationKIPiAS" :key="installationKIPiAOption.id">{{installationKIPiAOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.installationKIPiAGroup.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./installation-ki-pi-a-group-update.component.ts">
</script>
