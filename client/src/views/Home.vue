<template>
  <div class="page-enter-active">
    <!-- Hero 区域 -->
    <section class="relative overflow-hidden pb-12 pt-8">
      <div class="mx-auto max-w-7xl px-6">
        <div class="relative z-10">
          <h1 class="font-display text-4xl md:text-5xl lg:text-6xl font-bold leading-tight">
            发现你的<br />
            <span class="text-gradient">下一场演出</span>
          </h1>
          <p class="mt-4 text-lg text-white/40 max-w-lg">
            演唱会、话剧、体育赛事... 锁定你的专属座位
          </p>
        </div>

        <!-- 浮动装饰 -->
        <div class="absolute top-10 right-10 w-72 h-72 bg-brand-500/10 rounded-full blur-3xl"></div>
        <div class="absolute bottom-0 left-1/4 w-48 h-48 bg-accent-500/8 rounded-full blur-3xl"></div>
      </div>
    </section>

    <!-- 筛选栏 -->
    <section class="sticky top-16 z-40 border-b border-white/[0.05] bg-surface-950/80 backdrop-blur-lg">
      <div class="mx-auto max-w-7xl px-6 py-3">
        <div class="flex items-center gap-3 overflow-x-auto pb-1 scrollbar-none">
          <!-- 分类筛选 -->
          <button
            v-for="cat in categories"
            :key="cat.id"
            @click="activeCategory = activeCategory === cat.id ? null : cat.id"
            :class="[
              'whitespace-nowrap px-4 py-2 rounded-lg text-sm transition-all duration-200 shrink-0',
              activeCategory === cat.id
                ? 'bg-brand-500/20 text-brand-300 border border-brand-400/30'
                : 'bg-white/[0.03] text-white/50 border border-white/[0.05] hover:text-white/80 hover:border-white/10'
            ]"
          >
            {{ cat.name }}
          </button>

          <div class="w-px h-6 bg-white/[0.08] shrink-0"></div>

          <!-- 排序 -->
          <Menu as="div" class="relative shrink-0">
            <MenuButton
              class="flex items-center gap-1.5 px-3.5 py-2 rounded-lg text-sm
                     bg-white/[0.03] border border-white/[0.05] text-white/60
                     hover:text-white/80 hover:border-white/10
                     transition-all duration-200 cursor-pointer"
            >
              {{ sortLabel }}
              <svg
                class="w-3 h-3 transition-transform duration-200 ui-open:rotate-180"
                fill="none" stroke="currentColor" viewBox="0 0 24 24"
              >
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </MenuButton>

            <transition
              enter-active-class="transition duration-150 ease-out"
              enter-from-class="opacity-0 scale-95"
              enter-to-class="opacity-100 scale-100"
              leave-active-class="transition duration-100 ease-in"
              leave-from-class="opacity-100 scale-100"
              leave-to-class="opacity-0 scale-95"
            >
              <MenuItems
                class="absolute top-full right-0 mt-1.5 w-28
                       rounded-xl border border-white/[0.08]
                       bg-surface-800/95 backdrop-blur-xl
                       shadow-[0_12px_40px_rgba(0,0,0,0.5)]
                       py-1 z-50 overflow-hidden focus:outline-none"
              >
                <MenuItem
                  v-for="opt in sortOptions"
                  :key="opt.value"
                  v-slot="{ active }"
                >
                  <button
                    @click="sortBy = opt.value"
                    :class="[
                      'w-full text-left px-4 py-2.5 text-sm transition-colors duration-150',
                      active
                        ? 'text-brand-400 bg-brand-500/10'
                        : 'text-white/55 hover:text-white/85 hover:bg-white/[0.04]'
                    ]"
                  >
                    {{ opt.label }}
                  </button>
                </MenuItem>
              </MenuItems>
            </transition>
          </Menu>
        </div>
      </div>
    </section>

    <!-- 演出列表 -->
    <section class="mx-auto max-w-7xl px-6 py-8">
      <LoadingSpinner v-if="loading" />

      <template v-else-if="list.length > 0">
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-5">
          <div
            v-for="(event, idx) in list"
            :key="event.id"
            :style="{ animationDelay: `${idx * 60}ms` }"
            class="stagger-item"
          >
            <EventCard :event="event" />
          </div>
        </div>

        <!-- 分页 -->
        <div v-if="total > pageSize" class="mt-10 flex justify-center gap-2">
          <button
            @click="page--; fetchList()"
            :disabled="page <= 1"
            class="btn-ghost text-sm !px-4 !py-2 disabled:opacity-30"
          >
            上一页
          </button>
          <span class="flex items-center px-4 text-sm text-white/30">
            {{ page }} / {{ Math.ceil(total / pageSize) }}
          </span>
          <button
            @click="page++; fetchList()"
            :disabled="page >= Math.ceil(total / pageSize)"
            class="btn-ghost text-sm !px-4 !py-2 disabled:opacity-30"
          >
            下一页
          </button>
        </div>
      </template>

      <EmptyState v-else text="暂无演出" sub-text="换个筛选条件试试" />
    </section>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, onMounted, computed } from 'vue'
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/vue'
import { getEventList } from '@/api/event'
import type { EventCard as EventCardType, Category } from '@/types/common'
import EventCard from '@/components/EventCard.vue'
import LoadingSpinner from '@/components/LoadingSpinner.vue'
import EmptyState from '@/components/EmptyState.vue'

const list = ref<EventCardType[]>([])
const loading = ref(false)
const page = ref(1)
const pageSize = 20
const total = ref(0)
const activeCategory = ref<number | null>(null)
const sortBy = ref('recommend')
const sortOptions = [
  { value: 'recommend', label: '推荐' },
  { value: 'hot', label: '热度' },
  { value: 'time', label: '时间' },
  { value: 'price', label: '价格' },
]
const sortLabel = computed(() =>
  sortOptions.find(o => o.value === sortBy.value)?.label || '推荐'
)

// 模拟分类数据（后续从接口获取）
const categories = ref<Category[]>([
  { id: 1, name: '演唱会', parentId: null, sortOrder: 1 },
  { id: 2, name: '话剧', parentId: null, sortOrder: 2 },
  { id: 3, name: '音乐节', parentId: null, sortOrder: 3 },
  { id: 4, name: '体育赛事', parentId: null, sortOrder: 4 },
  { id: 5, name: '相声', parentId: null, sortOrder: 5 },
  { id: 6, name: '展览', parentId: null, sortOrder: 6 },
])

onMounted(() => fetchList())

watch([activeCategory, sortBy], () => {
  page.value = 1
  fetchList()
})

async function fetchList() {
  loading.value = true
  try {
    const data = await getEventList({
      categoryId: activeCategory.value ?? undefined,
      sortBy: sortBy.value,
      page: page.value,
      pageSize,
    })
    list.value = data.records
    total.value = data.total
  } catch {
    // 使用模拟数据展示
    list.value = getMockEvents()
    total.value = list.value.length
  } finally {
    loading.value = false
  }
}

const concertImages = [
  '/img/concertImage/48a6b04e28d7509a9f938ee0149d9676.jpg',
  '/img/concertImage/49ddbd6e03f4c40a2367b9c1e42e6550.jpg',
  '/img/concertImage/528a20ac75b692ca7df3e05ff8fe558d.jpg',
  '/img/concertImage/5e953e3f51aa65c5d81cbb542a790ac0.jpg',
  '/img/concertImage/8721a6ac9ed1919cdb9488a7736605e8.jpg',
  '/img/concertImage/99b34ff56833d032f32d0d220153db97.jpg',
  '/img/concertImage/ae4fdcefe55ae371e0e4f3a71bc49821.jpg',
  '/img/concertImage/ebd8fc35b20cdcfe1859d0cade81a158.jpg',
]

function getMockEvents(): EventCardType[] {
  const cities = ['北京', '上海', '广州', '深圳', '成都', '杭州']
  const venues = ['鸟巢文化中心', '梅赛德斯-奔驰文化中心', '星海音乐厅', '深圳湾体育中心', '成都大魔方', '杭州大剧院']
  const catNames = ['演唱会', '话剧', '音乐节', '体育赛事', '相声', '展览']
  const titles = [
    '周杰伦2026巡回演唱会',
    '《暗恋桃花源》经典话剧',
    '草莓音乐节·夏日专场',
    'CBA总决赛',
    '德云社相声专场',
    '莫奈印象派画展',
    '五月天巡回演唱会',
    '《雷雨》国家大剧院版',
  ]

  return Array.from({ length: 12 }, (_, i) => ({
    id: i + 1,
    title: titles[i % titles.length],
    posterUrl: concertImages[i % concertImages.length],
    categoryName: catNames[i % catNames.length],
    venueName: venues[i % venues.length],
    city: cities[i % cities.length],
    status: [0, 1, 1, 1, 1, 2, 1, 3][i % 8] as number,
    minPrice: [280, 380, 480, 580, 680, 880, 1280, 1680][i % 8],
    earliestSessionTime: new Date(Date.now() + i * 86400000 * 3).toISOString(),
  }))
}
</script>
