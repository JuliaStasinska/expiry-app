package pl.juliastasinska.expiryapp.items;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.juliastasinska.expiryapp.templates.TemplateFacade;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodTest {

    @Test
    @DisplayName("Should throw IllegalStateException when one wants to open food which is already eaten")
    void openFood_alreadyEaten_throwsIllegalStateException() {
        //given
        var mockRepository = mock(FoodRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(foodEitherEatenOrBinned(true));
        //and
        var toTest = new FoodFacade(mockRepository,null);
        //when
        var exception = catchThrowable(()->toTest.openFood(0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("eaten");
    }

    @Test
    @DisplayName("Should throw IllegalStateException when one wants to open food which is already binned")
    void openFood_alreadyBinned_throwsIllegalStateException() {
        //given
        var mockRepository = mock(FoodRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(foodEitherEatenOrBinned(false));
        //and
        var toTest = new FoodFacade(mockRepository,null);
        //when
        var exception = catchThrowable(()->toTest.openFood(0));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("binned");
    }

    @Test
    @DisplayName("Should change storageStatus to OPENED")
    void openFood_waiting() {
        //given
        var mockTemplateFacade = mock(TemplateFacade.class);
        when(mockTemplateFacade.readFood(anyInt())).thenReturn(Optional.of(new FoodTemplateDto(0,"bar","GENERIC",0,3,0)));
        //and
        InMemoryFoodRepository inMemoryFoodRepository=inMemoryFoodRepository();
        inMemoryFoodRepository.save(new Food("foo",mockTemplateFacade.readFood(anyInt()).get(),LocalDate.now()));
        //and
        var toTest = new FoodFacade(inMemoryFoodRepository, mockTemplateFacade);
        //and
        var openedFood=toTest.openFood(0);
        //then
        assertThat(openedFood.getStorageStatus()).isEqualTo("OPENED");
        assertThat(openedFood.getUseBy()).isAfterOrEqualTo(LocalDate.now().plusDays(3));
    }

    private Optional<Food> foodEitherEatenOrBinned(boolean eaten){
        var food=new Food("foo",null,LocalDate.now());
        if(eaten){
            food.eat();
        } else {
            food.bin();
        }
        return Optional.of(food);
    }

    InMemoryFoodRepository inMemoryFoodRepository(){
        return new InMemoryFoodRepository();
    };

    private static class InMemoryFoodRepository implements FoodRepository {
        int index=0;
        Map<Integer,Food> map = new HashMap<>();

        public int newCount() {
            return map.values().size();
        }

        @Override
        public List<Food> findAll() {
            return new ArrayList<>(map.values());
        }

        @Override
        public List<Food> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Food> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Food> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Food food) {

        }

        @Override
        public void deleteAll(Iterable<? extends Food> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Food> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<Food> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Food> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Food> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Food getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends Food> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Food> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Food> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Food> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Food> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Food> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public List<Food> findByDescriptionContaining(String nameFragment) {
            return null;
        }

        @Override
        public List<Food> findByUseBy(LocalDate date) {
            return null;
        }

        @Override
        public Optional<Food> findById(int id) {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public void deleteById(int id) {

        }

        public Food save(final Food entity) {

            map.put(entity.toDto().getId(), entity);

            return entity;
        }

    }

}