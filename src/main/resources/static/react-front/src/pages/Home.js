import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function Home() {
    const [directors, setdirectors]=useState([])

    useEffect(()=>{
        loadDirectors();
    }, []);

    const loadDirectors=async()=>{
        const result=await axios.get("http://localhost:8080/director/all");
        setdirectors(result.data);
    }

    return (
        <div className='container'>
            <div className='py-4'>
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Номер</th>
                            <th scope="col">Имя</th>
                            <th scope="col">Возраст</th>
                            <th scope="col">Страна</th>
                            <th scope="col">Действие</th>
                        </tr>
                    </thead>
                    <tbody>

                        {
                            directors.map((director, index)=>(
                                <tr>
                                    <th scope='row' key={index}>{index+1}</th>
                                    <td>{director.name}</td>
                                    <td>{director.age}</td>
                                    <td>{director.country}</td>
                                    <td>
                                        <button className='btn btn-primary mx-2'>View</button>
                                        <button className='btn btn-outline-primary mx-2'>Edit</button>
                                        <button className='btn btn-danger mx-2'>Delete</button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}
