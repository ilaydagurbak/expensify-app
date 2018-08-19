import { createStore } from 'redux' 

//Action generators

const incrementCount = ({incrementBy = 1 } = {}) => ({
    type: 'INCREMENT',
    incrementBy
})

const decrementCount = ({decrementBy = -1 } = {}) => ({
    type: 'DECREMENT',
    decrementBy
})

const store = createStore((state = { count: 0 }, action ) => {
    switch (action.type) {
        case 'INCREMENT' :
            return {
                count: state.count + action.incrementBy
            }
        case 'DECREMENT' :
            const decrementBy = typeof action.decrementBy === 'number' ? action.decrementBy : 1
            return {
                count: state.count - decrementBy
            }
        case 'SET' :
            return {
                count: action.count
            }
        case 'RESET' :
            return {
                count: 0
            }
        default:
            return state
    }
}) 

const unsubscribe = store.subscribe(() => {
    console.log(store.getState())
})

//Actions- than an object that gets sent to the store
store.dispatch({
    type: 'INCREMENT',
    incrementBy: 5 
})

store.dispatch(incrementCount())

//unsubscribe()

store.dispatch({
    type: 'DECREMENT',
    decrementBy: 10
})

store.dispatch({
    type: 'RESET'
})

store.dispatch(decrementCount())

store.dispatch({
    type: 'SET',
    count: 100
})
