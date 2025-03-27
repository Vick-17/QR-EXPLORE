/* eslint-disable no-unused-vars */
import { useState } from 'react';
import { get } from '../service/BackendService';
import { useEffect } from 'react';
import jwtDecode from "jwt-decode";

const UserProfil = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [favoritePlaces, setFavoritePlaces] = useState([]);
    const [placesToVisit, setPlacesToVisit] = useState([]);
    const [userComments, setUserComments] = useState([]);
    const [username, setUsername] = useState("");
    


    const visitedPlaces = ['Place 1', 'Place 2', 'Place 3'];

    const getFavoritePlaces = async () => {
        try {
            const favoritePlaces = await get('/users/favorites');
            setFavoritePlaces(favoritePlaces);
            setIsLoading(false);
        } catch (e) {
            console.error('Error fetching favorite places', e);
            setIsLoading(false);
        }
    }

    const getToLaterPlaces = async () => {
        try {
            const placesToVisit = await get('/users/toLater');
            setPlacesToVisit(placesToVisit);
        } catch (e) {
            console.error('Error fetching places to visit later', e);
        }
    }

    const getCommentByUserId = async () => {
        try {
            const comments = await get('/comments/getCommentsByUser');
            setUserComments(comments);
        } catch (e) {
            console.error('Error fetching comments', e);
    }
}

    useEffect(() => {
        getFavoritePlaces();
        getToLaterPlaces();
        getCommentByUserId();
        const token = localStorage.getItem("userToken");
        
        if (token) {
            const decodedToken = jwtDecode(token);
            setUsername(decodedToken.sub || "Utilisateur");
        }
    }, []);

    return (
        <div className="user-profil" style={{ padding: '20px' }}>
            <h1 className="user-profil__title">Bienvenue, <span className="user-profil__username">{username}</span></h1>
            <div className='user-profil__content'>
                <section className="user-profil__section visited-places">
                    <h2 className="section__title">Lieux déjà visités</h2>
                    <ul className="section__list">
                        {visitedPlaces.map((place) => (
                            <li key={place.id} className="section__list-item">{place}</li>
                        ))}
                    </ul>
                </section>

                <section className="user-profil__section favorite-places">
                    <h2 className="section__title">Favoris</h2>
                    <ul className="section__list">
                        {isLoading ? <p>Loading...</p> : favoritePlaces.map((place) => (
                            <li key={place.id} className="section__list-item">{place.name}</li>
                        ))}
                    </ul>
                </section>

                <section className="user-profil__section places-to-visit">
                    <h2 className="section__title">Lieux à visiter plus tard</h2>
                    <ul className="section__list">
                        {isLoading ? <p>Loading...</p> : placesToVisit.map((place) => (
                            <li key={place.id} className="section__list-item">{place.name}</li>
                        ))}
                    </ul>
                </section>

                <section className="user-profil__section user-comments">
                    <h2 className="section__title">Commentaires postés</h2>
                    <ul className="section__list">
                        {userComments.map((comment) => (
                            <li key={comment.id} className="section__list-item">
                                <strong className="comment__place">{comment.place.name}:</strong> <span className="comment__text">{comment.description}</span>
                            </li>
                        ))}
                    </ul>
                </section>
            </div>
        </div>
    );
};

export default UserProfil;