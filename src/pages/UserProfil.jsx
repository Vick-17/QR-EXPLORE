/* eslint-disable no-unused-vars */
import { useState } from 'react';
import { get } from '../service/BackendService';
import { useEffect } from 'react';

const UserProfil = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [favoritePlaces, setFavoritePlaces] = useState([]);
    const [placesToVisit, setPlacesToVisit] = useState([]);
    const [userComments, setUserComments] = useState([]);


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

    useEffect(() => {
        getFavoritePlaces();
    }, []);

    return (
        <div className="user-profil" style={{ padding: '20px' }}>
            <h1 className="user-profil__title">Profil Utilisateur</h1>

            <section className="user-profil__section visited-places">
                <h2 className="section__title">Lieux déjà visités</h2>
                <ul className="section__list">
                    {visitedPlaces.map((place, index) => (
                        <li key={index} className="section__list-item">{place}</li>
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
                    {placesToVisit.map((place, index) => (
                        <li key={index} className="section__list-item">{place}</li>
                    ))}
                </ul>
            </section>

            <section className="user-profil__section user-comments">
                <h2 className="section__title">Commentaires postés</h2>
                <ul className="section__list">
                    {userComments.map((comment, index) => (
                        <li key={index} className="section__list-item">
                            <strong className="comment__place">{comment.place}:</strong> <span className="comment__text">{comment.comment}</span>
                        </li>
                    ))}
                </ul>
            </section>
        </div>
    );
};

export default UserProfil;