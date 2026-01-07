<script lang="ts" setup>
import type {WorkDto} from "@/api/WorksApi";
import WorkDetailsPanel from "@/views/WorkDetailsPanel.vue";
import {useRouter} from "vue-router";

const props = defineProps<{
  work: WorkDto
  expanded: boolean
}>()

const emit = defineEmits<{
  (e: 'toggle', id: number): void
}>()

const router = useRouter()

function openDetails(){
  router.push(`/works/${props.work.id}`)
}

</script>

<template>
  <tr style="cursor: pointer" @click="emit('toggle', work.id)">
    <td>{{ work.id }}</td>
    <td>{{ work.title }}</td>
    <td>{{ work.composer?.name }} {{ work.composer?.surname }}</td>
    <td>{{ work.instrument?.name }}</td>
    <td>{{ work.difficulty }}</td>
    <td class="text-end">
      <div class="btn-group" role="group">
      <button class="btn btn-sm btn-outline-secondary" @click.stop="emit('toggle', work.id)">
        {{ expanded ? 'Hide' : 'Details' }}
      </button>
      <button class="btn btn-sm btn-outline-primary" @click.stop="openDetails">
        Open
      </button>
      </div>
    </td>
  </tr>

  <tr v-if="expanded" class="bg-light">
    <WorkDetailsPanel :work="work"/>
  </tr>
</template>

