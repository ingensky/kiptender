<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kiptenderApp.unitKIPiAGroup.home.createOrEditLabel" v-text="$t('kiptenderApp.unitKIPiAGroup.home.createOrEditLabel')">Create or edit a UnitKIPiAGroup</h2>
                <div>
                    <div class="form-group" v-if="unitKIPiAGroup.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="unitKIPiAGroup.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kiptenderApp.unitKIPiAGroup.quantity')" for="unit-ki-pi-a-group-quantity">Quantity</label>
                        <input type="number" class="form-control" name="quantity" id="unit-ki-pi-a-group-quantity"
                            :class="{'valid': !$v.unitKIPiAGroup.quantity.$invalid, 'invalid': $v.unitKIPiAGroup.quantity.$invalid }" v-model.number="$v.unitKIPiAGroup.quantity.$model" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('kiptenderApp.unitKIPiAGroup.unitKIPiA')" for="unit-ki-pi-a-group-unitKIPiA">Unit KI Pi A</label>
                        <select class="form-control" id="unit-ki-pi-a-group-unitKIPiA" multiple name="unitKIPiA" v-model="unitKIPiAGroup.unitKIPiAS">
                            <option v-bind:value="getSelected(unitKIPiAGroup.unitKIPiAS, unitKIPiAOption)" v-for="unitKIPiAOption in unitKIPiAS" :key="unitKIPiAOption.id">{{unitKIPiAOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.unitKIPiAGroup.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./unit-ki-pi-a-group-update.component.ts">
</script>
