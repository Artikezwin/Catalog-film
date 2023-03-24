import axios from 'axios'
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

export default function AddDirector() {
    
    let navigate=useNavigate()

    const [director, setDirector] = useState({
        name: "",
        age: "",
        country: ""
    })

    const { name, age, country } = director

    const onInputChange = (e) => {
        setDirector({ ...director, [e.target.name]: e.target.value })
    }

    const onSubmit =async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8080/director", director)
        navigate("/")
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Добавить режиссера</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className='mb-3'>
                            <label htmlFor="Name" className='form-label'>
                                Имя
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder='Введите имя режиссера'
                                name="name"
                                value={name}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Age" className='form-label'>
                                Возраст
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder='Введите возраст режиссера'
                                name="age"
                                value={age}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className='mb-3'>
                            <label htmlFor="Country" className='form-label'>
                                Страна
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder='Введите его страну проживания'
                                name="country"
                                value={country}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className='btn btn-outline-primary'>
                            Добавить
                        </button>
                        <Link className='btn btn-outline-danger mx-2' to="/">
                            Отменить
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
