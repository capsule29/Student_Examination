<template>
    <div>
        <h3>{{idx + 1}}. {{ question }}</h3>
        <br>
        <div>
            <label :for="idA" @mouseup="addAns(idx, optionA, questionId)">
                <input type="radio" :name="question" :id="idA"/>A.{{ optionA }}
            </label>
        </div>
        <div>
            <label :for="idB" @mouseup="addAns(idx, optionB, questionId)">
                <input type="radio" :name="question" :id="idB" />B.{{ optionB }}
            </label>
        </div>
        <hr>
    </div>
</template>


<script>

import { nanoid } from 'nanoid';

export default {
    name:'JudgeView',
    props: ['question', 'optionA', 'optionB','idx', 'questionId'],
    data () {
        return {
            idA: 0,
            idB: 0
        }
    },
    methods: {
        addAns (idx, v, questionId){
            this.$store.dispatch("judgeAns", { idx, v, questionId })
        }
    },
    mounted () {
        this.idA = nanoid()
        this.idB = nanoid()
    }
}
</script>

<style>

</style>