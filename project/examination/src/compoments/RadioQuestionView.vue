<template>
  <div>
        <h3>{{idx + 1}}. {{ question }}</h3>
        <br>
        <div>
            <label :for="idA" @mouseup="addAns(idx, optionA, questionId)">
                <input type="radio" :name="question" :id="idA" />A.{{ optionA }}
            </label>
        </div>

        <div>
            <label :for="idB" @mouseup="addAns(idx, optionB, questionId)">
                <input type="radio" :name="question" :id="idB" />B.{{ optionB }}
            </label>
        </div>

        <div>
            <label :for="idC" @mouseup="addAns(idx, optionC, questionId)">
                <input type="radio" :name="question" :id="idC" />C.{{ optionC }}
            </label>
        </div>

        <div>
            <label :for="idD" @mouseup="addAns(idx, optionD, questionId)">
                <input type="radio" :name="question" :id="idD" />D.{{ optionD }}
            </label>
        </div>
        <hr>
  </div>
</template>

<script>

import { nanoid } from 'nanoid';

export default {
    name: 'RadioView',
    props: ['question', 'optionA', 'optionB', 'optionC', 'optionD', 'idx', 'questionId'],
    data () {
        return {
            idA: 0,
            idB: 0,
            idC: 0,
            idD: 0,
        }
    },
    mounted () {
        this.idA = nanoid()
        this.idB = nanoid()
        this.idC = nanoid()
        this.idD = nanoid()
    },
    methods: {
        addAns (idx, v, questionId) {
            this.$store.dispatch("radioAns", { idx, v, questionId })
        }
    },
}
</script>

<style>

</style>