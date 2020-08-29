<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="unitKIPiAGroup">
                <h2 class="jh-entity-heading"><span v-text="$t('kiptenderApp.unitKIPiAGroup.detail.title')">UnitKIPiAGroup</span> {{unitKIPiAGroup.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiAGroup.quantity')">Quantity</span>
                    </dt>
                    <dd>
                        <span>{{unitKIPiAGroup.quantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kiptenderApp.unitKIPiAGroup.unitKIPiA')">Unit KI Pi A</span>
                    </dt>
                    <dd>
                        <span v-for="(unitKIPiA, i) in unitKIPiAGroup.unitKIPiAS" :key="unitKIPiA.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'UnitKIPiAView', params: {unitKIPiAId: unitKIPiA.id}}">{{unitKIPiA.id}}</router-link>
                        </span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="unitKIPiAGroup.id" :to="{name: 'UnitKIPiAGroupEdit', params: {unitKIPiAGroupId: unitKIPiAGroup.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unit-ki-pi-a-group-details.component.ts">
</script>
