<template>
    <div>
        <h3>{{idx + 1}}. {{ question }}</h3>
        <br>
        <div>
            <label :for="idA" @mouseup="change(0)">
                <input type="checkbox" :name="question" :id="idA" />A.{{ optionA }}
            </label>
        </div>

        <div>
            <label :for="idB" @mouseup="change(1)">
                <input type="checkbox" :name="question" :id="idB" />B.{{ optionB }}
            </label>
        </div>

        <div>
            <label :for="idC" @mouseup="change(2)">
                <input type="checkbox" :name="question" :id="idC" />C.{{ optionC }}
            </label>
        </div>

        <div>
            <label :for="idD" @mouseup="change(3)">
                <input type="checkbox" :name="question" :id="idD" />D.{{ optionD }}
            </label>
        </div>

        <div>
            <label :for="idE" @mouseup="change(4)">
                <input type="checkbox" :name="question" :id="idE" />E.{{ optionE }}
            </label>
        </div>
        <hr>
    </div>
</template>

<script>
import { nanoid } from 'nanoid';
export default {
    name: 'MultView',
    props: ['question', 'optionA', 'optionB', 'optionC', 'optionD', 'optionE', 'idx', 'questionId'],
    data () {
        return {
            idA: 0,
            idB: 0,
            idC: 0,
            idD: 0,
            idE: 0,
            select:[0,0,0,0,0],
        }
    },
    methods: {
        change (selectIdx) {
            this.select[selectIdx] = !this.select[selectIdx]
            var ansList = []
            for (var i = 0; i < 5; i++){
                if (i == 0 && this.select[i] == 1)
                    ansList.push(this.optionA)
                if (i == 1 && this.select[i] == 1)
                    ansList.push(this.optionB)
                if (i == 2 && this.select[i] == 1)
                    ansList.push(this.optionC)
                if (i == 3 && this.select[i] == 1)
                    ansList.push(this.optionD)
                if (i == 4 && this.select[i] == 1)
                    ansList.push(this.optionE)
            }
            this.addAns(this.idx, ansList, this.questionId)
        },
        addAns (idx, v, questionId) {
            this.$store.dispatch("multSelectAns", { idx, v, questionId })
        }
    },
    mounted () {
        this.idA = nanoid()
        this.idB = nanoid()
        this.idC = nanoid()
        this.idD = nanoid()
        this.idE = nanoid()
    }
}
</script>

<style>

</style>